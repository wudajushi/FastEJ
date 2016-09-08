package org.wuda.fastej.core;


import org.wuda.fastej.util.Assert;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Default type converter.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:41:54
 */
public class DefaultTypeConverter implements TypeConverter {
    /**
     * The constant logger.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:41:54
     */
    private static final Logger logger = LoggerFactory.getLogger(DefaultTypeConverter.class);
    /**
     * The constant INSTANCE.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:41:54
     */
    public static final DefaultTypeConverter INSTANCE = new DefaultTypeConverter();

    public static final FastDateFormat DEFAULT_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    public <T> T convert(Object value, Class<T> valueClass) {
        if(value == null) {
            return null;
        }
        Assert.notNull(valueClass, "valueClass must not be null !");
        return doConvert(String.valueOf(value), valueClass);
    }

    public int convertToInt(Object value) {
        if(value == null) {
            return 0;
        }
        return Integer.parseInt(String.valueOf(value));
    }

    public boolean convertToBoolean(Object value) {
        if(value == null) {
            return false;
        }
        String str = String.valueOf(value);
        return "true".equalsIgnoreCase(str) ? true : "false".equalsIgnoreCase(str) ? false : false;
    }

    public float convertToFloat(Object value) {
        if(value == null) {
            return 0.0f;
        }
        return Float.parseFloat(String.valueOf(value));
    }

    public char convertToChar(Object value) {
        if(value == null) {
            return '\u0000';
        }
        return String.valueOf(value).charAt(0);
    }

    public byte convertToByte(Object value) {
        if(value == null) {
            return 0;
        }
        return Byte.parseByte(String.valueOf(value));
    }

    public short convertToShort(Object value) {
        if(value == null) {
            return 0;
        }
        return Short.parseShort(String.valueOf(value));
    }

    public long convertToLong(Object value) {
        if(value == null) {
            return 0L;
        }
        return Long.parseLong(String.valueOf(value));
    }

    public double convertToDouble(Object value) {
        if(value == null) {
            return 0.0D;
        }
        return Double.parseDouble(String.valueOf(value));
    }

    public String convertDateToString(Date date, String pattern) {
        if(date == null) {
            return StringUtils.EMPTY;
        }
        try {
            return new SimpleDateFormat(pattern).format(date);
        } catch(IllegalArgumentException e) {
            logger.error("pattern[{}] is illegal!,use default pattern yyyy-MM-dd HH:mm:ss", pattern, e);
            return DEFAULT_DATE_FORMAT.format(date);
        }
    }

    /**
     * Do convert t.
     *
     * @param <T>        the type parameter
     * @param value      the value
     * @param valueClass the value class
     * @return the t
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:41:54
     */
    private <T> T doConvert(String value, Class<T> valueClass) {
        value = value.trim();
        if(StringUtils.isBlank(value)) {
            return null;
        }
        if(CharSequence.class.isAssignableFrom(valueClass)) {
            if(valueClass == String.class) {
                logger.debug("ValueClass is String.");
                return (T) value;
            }
            if(valueClass == StringBuffer.class) {
                logger.debug("ValueClass is StringBuffer.");
                return (T) new StringBuffer(value);
            }
            if(valueClass == StringBuilder.class) {
                logger.debug("ValueClass is StringBuilder.");
                return (T) new StringBuilder(value);
            }
        } else if(valueClass == Boolean.class) {
            logger.debug("ValueClass is Boolean.");
            return (T) ("true".equalsIgnoreCase(value) ? Boolean.TRUE : "false".equalsIgnoreCase(value) ? Boolean
                    .FALSE : null);
        } else if(Number.class.isAssignableFrom(valueClass)) {
            logger.debug("ValueClass is Number.");
            if(!NumberUtils.isNumber(value)) {
                logger.debug("ValueClass is Number . but origin value is not a number ! return null.");
                return null;
            }
            if(valueClass == Integer.class) {
                return (T) NumberUtils.createInteger(value);
            }
            if(valueClass == Double.class) {
                return (T) NumberUtils.createDouble(value);
            }
            if(valueClass == Float.class) {
                return (T) NumberUtils.createFloat(value);
            }
            if(valueClass == BigDecimal.class) {
                return (T) NumberUtils.createBigDecimal(value);
            }
            if(valueClass == Long.class) {
                return (T) NumberUtils.createLong(value);
            }
            if(valueClass == BigInteger.class) {
                return (T) NumberUtils.createBigInteger(value);
            }
        } else {
            if(valueClass == int.class) {
                return (T) Integer.valueOf(value);
            } else if(valueClass == double.class) {
                return (T) Double.valueOf(value);
            } else if(valueClass == long.class) {
                return (T) Long.valueOf(value);
            } else if(valueClass == char.class) {
                return (T) Character.valueOf(value.charAt(0));
            } else if(valueClass == boolean.class) {
                return (T) ("true".equalsIgnoreCase(value) ? Boolean.TRUE : "false".equalsIgnoreCase(value) ? Boolean
                        .FALSE : null);
            } else if(valueClass == byte.class) {
                return (T) Byte.valueOf(value);
            } else if(valueClass == float.class) {
                return (T) Float.valueOf(value);
            } else if(valueClass == short.class) {
                return (T) Short.valueOf(value);
            }
        }
        return null;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:33:24
     */
    public static void main(String[] args) {
        System.out.println(new DefaultTypeConverter().convert("111", int.class).getClass());
    }
}
