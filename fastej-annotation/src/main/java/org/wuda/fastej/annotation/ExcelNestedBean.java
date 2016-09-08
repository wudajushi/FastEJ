package org.wuda.fastej.annotation;

import java.lang.annotation.*;

/**
 * The interface Excel nested bean.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:41:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface ExcelNestedBean {
    /**
     * Index int.
     *
     * @return the int
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:41:48
     */
    int index() default 0;

    /**
     * Column name string.
     *
     * @return the string
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:41:48
     */
    String columnName();

    /**
     * Is getter boolean.
     *
     * @return the boolean
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 16:03:33
     */
    boolean isGetter() default false;
}
