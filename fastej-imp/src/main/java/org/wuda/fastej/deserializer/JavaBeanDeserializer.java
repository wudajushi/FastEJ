package org.wuda.fastej.deserializer;

import org.wuda.fastej.annotation.ExcelType;
import org.wuda.fastej.core.*;
import org.wuda.fastej.util.Assert;
import org.wuda.fastej.util.CollectionUtils;
import org.wuda.fastej.util.StringUtils;
import org.wuda.fastej.validate.EJBeanValidator;
import org.wuda.fastej.validate.EJDefaultBeanValidator;
import org.wuda.fastej.validate.ValidatedBean;
import org.wuda.fastej.validate.placeholder.EJColumnNamePlaceholderProcessor;
import org.wuda.fastej.validate.placeholder.EJRowIndexPlaceholderProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Java bean deserializer.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:55
 */
public class JavaBeanDeserializer implements BeanDeserializer {
    /**
     * The constant CURRENT_EXCELBEAN.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:55
     */
    public final static ThreadLocal<ExcelBeanBinderData> CURRENT_EXCELBEAN = new ThreadLocal<ExcelBeanBinderData>();
    /**
     * The Factory.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:55
     */
    private BeanDeserializerFactory factory = new ASMBeanDeserializerFactory();
    //    private BeanDeserializerFactory factory = new ReflectionBeanDeserializerFactory();
    /**
     * The Ej bean validator.
     * Bean的验证主要逻辑都在这个验证器里。
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 17:08:15
     */
    private EJBeanValidator ejBeanValidator = new EJDefaultBeanValidator();

    public JavaBeanDeserializer() {
        //注册RowIndex的占位符解析器
        ejBeanValidator.registerPlaceholderProcessor(new EJRowIndexPlaceholderProcessor());
        //注册ColumnName的占位符解析器
        ejBeanValidator.registerPlaceholderProcessor(new EJColumnNamePlaceholderProcessor());
    }

    public <T> Map<Object, ValidatedBean<T>> deserializeWithValidate(ExcelRawData rawData, ExcelClassInfo
            excelClassInfo) {
        List<T> beanList = deserialize(rawData, excelClassInfo);
        return ejBeanValidator.validate(beanList);
    }


    public void setValidator(EJBeanValidator ejBeanValidator) {
        Assert.notNull(ejBeanValidator, "EJBeanValidator must not be null !");
        this.ejBeanValidator = ejBeanValidator;
    }

    public <T> List<T> deserialize(ExcelRawData rawData, ExcelClassInfo excelClassInfo) {
        Assert.notNull(rawData, "ExcelRawData must not be null !");
        Assert.notNull(excelClassInfo, "ExcelClassInfo must not be null!");
        ExcelType inputType = excelClassInfo.getInputType();
        ExcelType excelType = rawData.getExcelType();
        Assert.isTrue(inputType.equals(excelType), "Excel type is not equals to bean annotation input type !");
        List<Map<String, String>> datas = rawData.getDatas();
        if(CollectionUtils.isEmpty(datas)) {
            return new ArrayList<T>();
        }
        Class<T> beanClass = (Class<T>) excelClassInfo.getClass();
        Map<String, ExcelBaseFieldInfo> fieldInfoMap = excelClassInfo.getColumnInfo();
        if(CollectionUtils.isEmpty(fieldInfoMap)) {
            return new ArrayList<T>();
        }
        List<Map<String, ExcelCellData>> allCellDatas = new ArrayList<Map<String, ExcelCellData>>();
        for(Map<String, String> row : datas) {
            if(CollectionUtils.isEmpty(row)) {
                continue;
            }
            Map<String, ExcelCellData> rowCellDatas = new HashMap<String, ExcelCellData>();
            for(Map.Entry<String, String> entryItem : row.entrySet()) {
                String columnName = entryItem.getKey();
                if(!StringUtils.hasLength(columnName)) {
                    continue;
                }
                String value = entryItem.getValue();
                ExcelBaseFieldInfo fieldInfo = fieldInfoMap.get(columnName);
                if(fieldInfo == null) {
                    continue;
                }
                String fieldName = fieldInfo.getFieldName();
                rowCellDatas.put(fieldName, new ExcelCellData(fieldName, fieldInfo, value));
            }
            allCellDatas.add(rowCellDatas);
        }
        try {
            CURRENT_EXCELBEAN.set(new ExcelBeanBinderData(allCellDatas, excelClassInfo));
            ASMGeneratorDeserializer gen = factory.gen(excelClassInfo);
            List<T> resultList = gen.deserialize();
            return resultList;
        } finally {
            CURRENT_EXCELBEAN.remove();
        }
    }
}
