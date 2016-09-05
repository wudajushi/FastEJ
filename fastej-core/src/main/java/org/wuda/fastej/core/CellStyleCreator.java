package org.wuda.fastej.core;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * The interface Cell style creator.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-08-26 17:23:20
 */
public interface CellStyleCreator {
    /**
     * 生成header表头的样式
     *
     * @param workbook the workbook
     * @return the cell style
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:23:20
     */
    CellStyle createHeaderRowStyle(Workbook workbook);

    /**
     * 生成数据行的样式
     *
     * @param workbook   the workbook
     * @param sheetIndex the sheet index
     * @param rowIndex   the row index
     * @return the cell style
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:23:20
     */
    CellStyle createDataRowStyle(Workbook workbook, String sheetName, int rowIndex);
}
