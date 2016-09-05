package org.wuda.fastej.sax;

import java.util.List;

/**
 * Sax读取Excel的行处理器
 *
 * @param <T> the type parameter
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-26 17:16:07
 */
public interface SaxRowProcessor<T> {
    /**
     * Process row t.
     *
     * @param sheetIndex the sheet index
     * @param rowIndex   the row index
     * @param cellValues the cell values
     * @return the t
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:16:07
     */
    T processRow(int sheetIndex, int rowIndex, List<String> cellValues);

    /**
     * Process document end.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:16:07
     */
    void processDocumentEnd();

    /**
     * Is finished boolean.
     *
     * @return the boolean
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:40:16
     */
    boolean isFinished();
}
