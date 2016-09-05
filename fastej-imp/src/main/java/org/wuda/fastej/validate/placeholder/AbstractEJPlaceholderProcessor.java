package org.wuda.fastej.validate.placeholder;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Abstract ej placeholder processor.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-28 16:50:00
 */
public abstract class AbstractEJPlaceholderProcessor implements EJPlaceholderProcessor {
    protected String originPlaceholder = EJ_PLACEHOLDER;
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected String replacePlaceholder(String originPlaceholder, String actualPlaceholder, String originMessage,
                                        Object value) {
        String strValue = StringUtils.EMPTY;
        if(value != null) {
            strValue = value.toString();
        }
        if(originMessage == null) {
            return StringUtils.EMPTY;
        }
        return originMessage.replace(String.format(originPlaceholder, actualPlaceholder), strValue);
    }

    public void setEjPlaceholder(String ejPlaceholder) {
        //no op
    }
}
