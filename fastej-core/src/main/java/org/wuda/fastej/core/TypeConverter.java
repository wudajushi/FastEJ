package org.wuda.fastej.core;

import java.util.Date;

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

    /**
     * Convert to int int.
     *
     * @param value the value
     * @return the int
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:32:27
     */
    int convertToInt(Object value);

    /**
     * Convert to boolean boolean.
     *
     * @param value the value
     * @return the boolean
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:32:27
     */
    boolean convertToBoolean(Object value);

    /**
     * Convert to float float.
     *
     * @param value the value
     * @return the float
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:32:27
     */
    float convertToFloat(Object value);

    /**
     * Convert to char char.
     *
     * @param value the value
     * @return the char
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:32:27
     */
    char convertToChar(Object value);

    /**
     * Convert to byte byte.
     *
     * @param value the value
     * @return the byte
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:32:27
     */
    byte convertToByte(Object value);

    /**
     * Convert to short short.
     *
     * @param value the value
     * @return the short
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:32:27
     */
    short convertToShort(Object value);

    /**
     * Convert to long long.
     *
     * @param value the value
     * @return the long
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:32:27
     */
    long convertToLong(Object value);

    /**
     * Convert to double double.
     *
     * @param value the value
     * @return the double
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:32:27
     */
    double convertToDouble(Object value);

    /**
     * Convert date to string string.
     *
     * @param date    the date
     * @param pattern the pattern
     * @return the string
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:32:27
     */
    String convertDateToString(Date date, String pattern);
}
