package org.wuda.fastej.annotation;

import java.lang.annotation.*;

/**
 * The interface Excel field.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:41:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ExcelField {
    /**
     * Index int.
     *
     * @return the int
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:41:40
     */
    int index() default 0;

    /**
     * Column name string.
     *
     * @return the string
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:41:40
     */
    String columnName();

    /**
     * Is getter boolean.
     *
     * @return the boolean
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 16:03:18
     */
    boolean isGetter() default false;

    /**
     * Date pattern string.
     *
     * @return the string
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:20:38
     */
    String datePattern() default "yyyy-MM-dd HH:mm:ss";
}
