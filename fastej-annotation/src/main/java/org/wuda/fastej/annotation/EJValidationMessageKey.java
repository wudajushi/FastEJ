package org.wuda.fastej.annotation;

import java.lang.annotation.*;

/**
 * 验证时以哪个字段值为Key
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-28 16:00:55
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface EJValidationMessageKey {
    /**
     * Message key string.
     *
     * @return the string
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 16:00:55
     */
    String messageKey();
}
