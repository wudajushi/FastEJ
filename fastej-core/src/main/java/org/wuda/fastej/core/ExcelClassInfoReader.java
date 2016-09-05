package org.wuda.fastej.core;

import org.wuda.fastej.annotation.ExcelBean;
import org.wuda.fastej.annotation.ExcelField;
import org.wuda.fastej.annotation.ExcelNestedBean;
import org.wuda.fastej.annotation.ExcelType;
import org.wuda.fastej.util.Assert;
import org.wuda.fastej.util.ObjectUtils;
import org.wuda.fastej.util.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Excel class info reader.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:23
 */
public class ExcelClassInfoReader {
    /**
     * The constant logger.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:23
     */
    private static final Logger logger = LoggerFactory.getLogger(ExcelClassInfoReader.class);

    /**
     * Read excel class info excel class info.
     *
     * @param beanClass the bean class
     * @return the excel class info
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:23
     */
    public static ExcelClassInfo readExcelClassInfo(final Class<?> beanClass) {
        Assert.notNull(beanClass, "Bean class must not be null !");
        Annotation[] annotations = beanClass.getAnnotations();
        Assert.notEmpty(annotations, "Class [" + beanClass.getName() + "] annotations must not be empty !");
        ExcelType inputType = null;
        ExcelType outputType = null;
        ExcelBean excelBean = null;
        for(Annotation annotation : annotations) {
            if(annotation instanceof ExcelBean) {
                excelBean = (ExcelBean) annotation;
                inputType = excelBean.inputType();
                outputType = excelBean.outputType();
            }
        }
        if(excelBean == null) {
            return null;
        }
        Object[] maps = resolveClass(beanClass);
        return new ExcelClassInfo(beanClass, (Map<String, ExcelBaseFieldInfo>) maps[0], (Map<String,
                ExcelBaseFieldInfo>) maps[1], inputType, outputType);
    }

    /**
     * Resolve class map.
     *
     * @param beanClass the bean class
     * @return the map
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:23
     */
    private static Object[] resolveClass(final Class<?> beanClass) {
        Field[] fields = beanClass.getDeclaredFields();
        Assert.notEmpty(fields, "Class [" + beanClass.getName() + "] fields must not be empty !");
        Map<String, ExcelBaseFieldInfo> columnMap = new HashMap<String, ExcelBaseFieldInfo>();
        Map<String, ExcelBaseFieldInfo> fieldMap = new HashMap<String, ExcelBaseFieldInfo>();
        resolveFieldInfo(fields, columnMap, fieldMap);
        return new Object[]{columnMap, fieldMap};
    }

    /**
     * Resolve field info.
     *
     * @param fields   the fields
     * @param fieldMap the field map
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:23
     */
    private static void resolveFieldInfo(Field[] fields, Map<String, ExcelBaseFieldInfo> columnMap, Map<String,
            ExcelBaseFieldInfo> fieldMap) {
        for(Field field : fields) {
            Annotation[] fieldAnnotations = field.getAnnotations();
            if(ObjectUtils.isEmpty(fieldAnnotations)) {
                if(logger.isDebugEnabled()) {
                    logger.debug("Field {} has no annotations ! continue ", field.getName());
                }
                continue;
            }
            Class<?> fieldClass = field.getType();
            boolean isBaseType = TypeUtils.isBaseType(fieldClass);
            String fieldName = field.getName();
            String columnName;
            int index;
            for(Annotation fieldAnnotation : fieldAnnotations) {
                if(fieldAnnotation instanceof ExcelField) {
                    if(!isBaseType) {
                        throw new EJTypeMismatchException("The field[" + fieldName + "] is not a baseType ,cannot use" +
                                " " +
                                "ExcelField annotation !");
                    }
                    ExcelField excelField = (ExcelField) fieldAnnotation;
                    columnName = excelField.columnName().trim();
                    index = excelField.index();
                    ExcelBaseFieldInfo excelFieldInfo = new ExcelBaseFieldInfo(field.getType(), fieldName,
                            columnName, index, null, null, false);
                    columnMap.put(columnName, excelFieldInfo);
                    fieldMap.put(fieldName, excelFieldInfo);
                } else if(fieldAnnotation instanceof ExcelNestedBean) {
                    if(isBaseType) {
                        throw new EJTypeMismatchException("The field[" + fieldName + "] is a baseType ,cannot use " +
                                "ExcelNestedBean annotation !");
                    }
                    ExcelNestedBean excelNestedBean = (ExcelNestedBean) fieldAnnotation;
                    columnName = excelNestedBean.columnName().trim();
                    index = excelNestedBean.index();
                    Object[] maps = resolveClass(fieldClass);
                    ExcelBaseFieldInfo excelFieldInfo = new ExcelBaseFieldInfo(field.getType(), fieldName,
                            columnName, index, (Map<String, ExcelBaseFieldInfo>) maps[0], (Map<String,
                            ExcelBaseFieldInfo>) maps[1], true);
                    columnMap.put(columnName, excelFieldInfo);
                    fieldMap.put(fieldName, excelFieldInfo);
                }
            }
        }
    }
}
