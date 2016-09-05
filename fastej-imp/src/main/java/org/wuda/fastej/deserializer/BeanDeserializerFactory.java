package org.wuda.fastej.deserializer;

import org.wuda.fastej.core.ExcelClassInfo;
import org.wuda.fastej.core.ExcelClassInfo;

/**
 * The interface Bean deserializer factory.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:48
 */
public interface BeanDeserializerFactory {
    /**
     * Gen asm generator deserializer.
     *
     * @param excelClassInfo the excel class info
     * @return the asm generator deserializer
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:48
     */
    ASMGeneratorDeserializer gen(ExcelClassInfo excelClassInfo);
}
