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
@Target(ElementType.FIELD)
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

}
