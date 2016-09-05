package org.wuda.fastej.annotation;

import java.lang.annotation.*;

/**
 * The interface Excel bean.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:41:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target(ElementType.TYPE)
public @interface ExcelBean {
    /**
     * Input type excel type.
     *
     * @return the excel type
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:41:25
     */
    ExcelType inputType();

    /**
     * Output type excel type.
     *
     * @return the excel type
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:41:25
     */
    ExcelType outputType();
}
