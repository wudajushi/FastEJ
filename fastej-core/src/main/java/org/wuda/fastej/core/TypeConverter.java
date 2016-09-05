package org.wuda.fastej.core;

/**
 * The interface Type converter.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:34
 */
public interface TypeConverter {
    /**
     * Convert t.
     *
     * @param <T>        the type parameter
     * @param value      the value
     * @param valueClass the value class
     * @return the t
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:34
     */
    <T> T convert(Object value, Class<T> valueClass);

    int convertToInt(Object value);

    boolean convertToBoolean(Object value);

    float convertToFloat(Object value);

    char convertToChar(Object value);

    byte convertToByte(Object value);

    short convertToShort(Object value);

    long convertToLong(Object value);

    double convertToDouble(Object value);
}
