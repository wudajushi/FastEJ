package org.wuda.fastej.deserializer;

import java.util.List;

/**
 * The interface Asm generator deserializer.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:42
 */
public interface ASMGeneratorDeserializer {
    /**
     * Deserialize list.
     *
     * @param <T> the type parameter
     * @return the list
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:42
     */
    <T> List<T> deserialize();
}
