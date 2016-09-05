package org.wuda.fastej.deserializer;

import org.wuda.fastej.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Reflection bean deserializer factory.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:57
 */
public class ReflectionBeanDeserializerFactory implements BeanDeserializerFactory {
    /**
     * The constant logger.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:57
     */
    private static final Logger logger = LoggerFactory.getLogger(ReflectionBeanDeserializerFactory.class);

    public ASMGeneratorDeserializer gen(final ExcelClassInfo excelClassInfo) {
        return new ASMGeneratorDeserializer() {
            public <T> List<T> deserialize() {
                Class<?> clazz = excelClassInfo.getRawClass();
                ExcelBeanBinderData binderData = JavaBeanDeserializer.CURRENT_EXCELBEAN.get();
                try {
                    List<T> resultList = new ArrayList<T>();
                    TypeConverter typeConverter = DefaultTypeConverter.INSTANCE;
                    for(Map<String, ExcelCellData> rowDatas : binderData.getAllCellDatas()) {
                        Object obj = clazz.newInstance();
                        for(Map.Entry<String, ExcelCellData> entryItem : rowDatas.entrySet()) {
                            String fieldName = entryItem.getKey();
                            String setter = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                            ExcelCellData cellData = entryItem.getValue();
                            Method setMethod = clazz.getMethod("set" + setter, cellData.getFieldInfo().getFieldClass());
                            setMethod.invoke(obj, typeConverter.convert(cellData.getCellData(), cellData.getFieldInfo().getFieldClass()));
                        }
                        resultList.add((T) obj);
                    }
                    return resultList;
                } catch(Exception e) {
                    logger.error("Instantiate object error ! Class[{}]", clazz.getName(), e);
                    throw new EJBeanInstantiationException("Instantiate object error ! Class[" + clazz.getName() + "]", e);
                }
            }
        };
    }
}
