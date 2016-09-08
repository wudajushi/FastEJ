package org.wuda.fastej.core;

import org.wuda.fastej.annotation.ExcelBean;
import org.wuda.fastej.annotation.ExcelField;
import org.wuda.fastej.annotation.ExcelNestedBean;
import org.wuda.fastej.annotation.ExcelType;
import org.wuda.fastej.util.Assert;
import org.wuda.fastej.util.CollectionUtils;
import org.wuda.fastej.util.ObjectUtils;
import org.wuda.fastej.util.TypeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        Map<String, ExcelBaseFieldInfo> columnMap = new HashMap<String, ExcelBaseFieldInfo>();
        Map<String, ExcelBaseFieldInfo> fieldMap = new HashMap<String, ExcelBaseFieldInfo>();
        List<Field> fieldsList = findAllDeclaredFields(beanClass);
        List<Method> methodsList = findAllDeclaredMethods(beanClass);
        if(CollectionUtils.isEmpty(fieldsList) && CollectionUtils.isEmpty(methodsList)) {
            logger.error("Class[{}] has no fields or methods !", beanClass.getName());
            return null;
        }
        if(!CollectionUtils.isEmpty(fieldsList)) {
            Field[] fields = fieldsList.toArray(new Field[0]);
            resolveFieldInfo(fields, columnMap, fieldMap);
        }
        if(!CollectionUtils.isEmpty(methodsList)) {
            Method[] methods = methodsList.toArray(new Method[0]);
            resolveMethodInfo(methods, columnMap, fieldMap);
        }
        return new Object[]{columnMap, fieldMap};
    }

    /**
     * Resolve method info.
     *
     * @param methods   the methods
     * @param columnMap the column map
     * @param fieldMap  the field map
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 15:57:23
     */
    private static void resolveMethodInfo(Method[] methods, Map<String, ExcelBaseFieldInfo> columnMap, Map<String,
            ExcelBaseFieldInfo> fieldMap) {
        for(Method method : methods) {
            Class<?> methodType = method.getReturnType();
            String name = StringUtils.uncapitalize(method.getName().replace("get", ""));
            resolveAnnotationInfo(method.getAnnotations(), name, methodType, columnMap, fieldMap, true);
        }
    }

    /**
     * Resolve annotation info.
     *
     * @param annotations the annotations
     * @param fieldName   the field name
     * @param type        the type
     * @param columnMap   the column map
     * @param fieldMap    the field map
     * @param isMethod    the is method
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 16:13:46
     */
    private static void resolveAnnotationInfo(Annotation[] annotations, String fieldName, Class<?> type, Map<String,
            ExcelBaseFieldInfo> columnMap, Map<String, ExcelBaseFieldInfo> fieldMap, boolean isMethod) {
        if(ObjectUtils.isEmpty(annotations)) {
            if(logger.isDebugEnabled()) {
                logger.debug("Field {} has no annotations ! continue ", fieldName);
            }
            return;
        }
        boolean isBaseType = TypeUtils.isBaseType(type);
        String columnName;
        int index;
        String datePattern;
        for(Annotation fieldAnnotation : annotations) {
            if(fieldAnnotation instanceof ExcelField) {
                if(!isBaseType) {
                    throw new EJTypeMismatchException("The field[" + fieldName + "] is not a baseType ,cannot use" +
                            " " +
                            "ExcelField annotation !");
                }
                ExcelField excelField = (ExcelField) fieldAnnotation;
                boolean isGetter = excelField.isGetter();
                if(isMethod && isMethod != isGetter) {
                    throw new FastEJException("The field[" + fieldName + "] is a method ,isGetter must be true !");
                }
                if(isGetter && isMethod != isGetter) {
                    throw new FastEJException("The field[" + fieldName + "] is a field ,isGetter must be false !");
                }
                columnName = excelField.columnName().trim();
                index = excelField.index();
                datePattern = excelField.datePattern();
                ExcelBaseFieldInfo excelFieldInfo = new ExcelBaseFieldInfo(type, fieldName, columnName, index, null,
                        null, false, isGetter, datePattern);
                columnMap.put(columnName, excelFieldInfo);
                fieldMap.put(fieldName, excelFieldInfo);
            } else if(fieldAnnotation instanceof ExcelNestedBean) {
                if(isBaseType) {
                    throw new EJTypeMismatchException("The field[" + fieldName + "] is a baseType ,cannot use " +
                            "ExcelNestedBean annotation !");
                }
                ExcelNestedBean excelNestedBean = (ExcelNestedBean) fieldAnnotation;
                columnName = excelNestedBean.columnName().trim();
                boolean isGetter = excelNestedBean.isGetter();
                if(isMethod && isMethod != isGetter) {
                    throw new FastEJException("The field[" + fieldName + "] is a method ,isGetter must be true !");
                }
                if(isGetter && isMethod != isGetter) {
                    throw new FastEJException("The field[" + fieldName + "] is a field ,isGetter must be false !");
                }
                index = excelNestedBean.index();
                Object[] maps = resolveClass(type);
                ExcelBaseFieldInfo excelFieldInfo = new ExcelBaseFieldInfo(type, fieldName, columnName, index,
                        (Map<String, ExcelBaseFieldInfo>) maps[0], (Map<String, ExcelBaseFieldInfo>) maps[1], true,
                        isGetter, null);
                columnMap.put(columnName, excelFieldInfo);
                fieldMap.put(fieldName, excelFieldInfo);
            }
        }
    }

    /**
     * Find all declared fields list.
     *
     * @param beanClass the bean class
     * @return the list
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 15:57:23
     */
    public static List<Field> findAllDeclaredFields(Class<?> beanClass) {
        List<Field> fields = new ArrayList<Field>();
        Field[] fs = beanClass.getDeclaredFields();
        for(Field f : fs) {
            fields.add(f);
        }
        if(beanClass.getSuperclass() == Object.class) {
            return fields;
        }
        List<Field> recursiveList = findAllDeclaredFields(beanClass.getSuperclass());
        fields.addAll(recursiveList);
        return fields;
    }

    /**
     * Find all declared methods list.
     *
     * @param beanClass the bean class
     * @return the list
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 16:13:46
     */
    public static List<Method> findAllDeclaredMethods(Class<?> beanClass) {
        List<Method> methods = new ArrayList<Method>();
        Method[] ms = beanClass.getDeclaredMethods();
        for(Method m : ms) {
            methods.add(m);
        }
        if(beanClass.getSuperclass() == Object.class) {
            return methods;
        }
        List<Method> recursiveList = findAllDeclaredMethods(beanClass.getSuperclass());
        methods.addAll(recursiveList);
        return methods;
    }

    /**
     * Resolve field info.
     *
     * @param fields    the fields
     * @param columnMap the column map
     * @param fieldMap  the field map
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:23
     */
    private static void resolveFieldInfo(Field[] fields, Map<String, ExcelBaseFieldInfo> columnMap, Map<String,
            ExcelBaseFieldInfo> fieldMap) {
        for(Field field : fields) {
            Class<?> fieldClass = field.getType();
            String fieldName = field.getName();
            resolveAnnotationInfo(field.getAnnotations(), fieldName, fieldClass, columnMap, fieldMap, false);
        }
    }
}
