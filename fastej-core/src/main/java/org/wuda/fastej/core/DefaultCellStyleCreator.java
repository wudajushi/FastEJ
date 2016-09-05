package org.wuda.fastej.core;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.RegionUtil;

/**
 * 默认样式生成器
 * <b>Note:线程安全</b>
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-08-26 17:25:06
 */
public class DefaultCellStyleCreator implements CellStyleCreator {
    /**
     * The constant INSTANCE.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 18:10:31
     */
    public static final DefaultCellStyleCreator INSTANCE = new DefaultCellStyleCreator();

    /**
     * The Cache.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:37:29
     */
    public CellStyle createHeaderRowStyle(Workbook workbook) {
        if(workbook == null) {
            return null;
        }
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBottomBorderColor(IndexedColors.BLUE.getIndex());
        headerCellStyle.setTopBorderColor(IndexedColors.BLUE.getIndex());
        headerCellStyle.setLeftBorderColor(IndexedColors.BLUE.getIndex());
        headerCellStyle.setRightBorderColor(IndexedColors.BLUE.getIndex());
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.DARK_GREEN.getIndex());
        headerCellStyle.setFont(font);
        return headerCellStyle;
    }

    public CellStyle createDataRowStyle(Workbook workbook, String sheetName, int rowIndex) {
        if(workbook == null) {
            return null;
        }
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);
        dataCellStyle.setBottomBorderColor(IndexedColors.GREEN.getIndex());
        dataCellStyle.setTopBorderColor(IndexedColors.GREEN.getIndex());
        dataCellStyle.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        dataCellStyle.setRightBorderColor(IndexedColors.GREEN.getIndex());
        return dataCellStyle;
    }
}
