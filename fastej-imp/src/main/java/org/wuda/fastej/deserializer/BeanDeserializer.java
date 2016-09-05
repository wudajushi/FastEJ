package org.wuda.fastej.deserializer;

import org.wuda.fastej.core.ExcelClassInfo;
import org.wuda.fastej.core.ExcelRawData;
import org.wuda.fastej.validate.EJBeanValidator;
import org.wuda.fastej.validate.ValidatedBean;

import java.util.List;
import java.util.Map;

/**
 * The interface Bean deserializer.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:45
 */
public interface BeanDeserializer extends EJDeserializer {

    /**
     * Deserialize list.
     *
     * @param <T>            the type parameter
     * @param rawData        the raw data
     * @param excelClassInfo the excel class info
     * @return the list
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:45
     */
    <T> List<T> deserialize(ExcelRawData rawData, ExcelClassInfo excelClassInfo);

    /**
     * 带验证的反序列化接口
     *
     * @param <T>            the type parameter
     * @param rawData        the raw data
     * @param excelClassInfo the excel class info
     * @return the map
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 17:23:46
     */
    <T> Map<Object, ValidatedBean<T>> deserializeWithValidate(ExcelRawData rawData, ExcelClassInfo excelClassInfo);

    /**
     * Sets validator.
     *
     * @param ejBeanValidator the ej bean validator
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 17:12:57
     */
    void setValidator(EJBeanValidator ejBeanValidator);
}
