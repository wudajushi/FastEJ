package org.wuda.fastej.util;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Type utils.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:43:17
 */
public class TypeUtils {
    /**
     * The constant primitiveClasses.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:43:17
     */
    private static final List<Class<?>> primitiveClasses = new ArrayList<Class<?>>();
    /**
     * The constant boxedPrimitiveClasses.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:43:17
     */
    private static final List<Class<?>> boxedPrimitiveClasses = new ArrayList<Class<?>>();
    /**
     * The constant frequenlyBaseClasses.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:43:17
     */
    private static final List<Class<?>> frequenlyBaseClasses = new ArrayList<Class<?>>();

    static {
        primitiveClasses.add(int.class);
        primitiveClasses.add(boolean.class);
        primitiveClasses.add(short.class);
        primitiveClasses.add(long.class);
        primitiveClasses.add(double.class);
        primitiveClasses.add(float.class);
        primitiveClasses.add(byte.class);
        primitiveClasses.add(char.class);
        boxedPrimitiveClasses.add(Integer.class);
        boxedPrimitiveClasses.add(Boolean.class);
        boxedPrimitiveClasses.add(Short.class);
        boxedPrimitiveClasses.add(Long.class);
        boxedPrimitiveClasses.add(Double.class);
        boxedPrimitiveClasses.add(Float.class);
        boxedPrimitiveClasses.add(Byte.class);
        boxedPrimitiveClasses.add(Character.class);
        frequenlyBaseClasses.add(CharSequence.class);
        frequenlyBaseClasses.add(Number.class);
    }

    /**
     * Is base type boolean.
     *
     * @param fieldClass the field class
     * @return the boolean
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:43:17
     */
    public static boolean isBaseType(Class<?> fieldClass) {
        Assert.notNull(fieldClass, "Field class must not be null !");
        boolean isPrimitive = primitiveClasses.contains(fieldClass) || boxedPrimitiveClasses.contains(fieldClass);
        if(!isPrimitive) {
            for(Class<?> classItem : frequenlyBaseClasses) {
                return classItem.isAssignableFrom(fieldClass);
            }
        }
        return isPrimitive;
    }

    public static boolean isPrimitive(Class<?> fieldClass) {
        return primitiveClasses.contains(fieldClass);
    }
}
