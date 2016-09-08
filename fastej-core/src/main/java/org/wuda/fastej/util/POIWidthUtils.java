package org.wuda.fastej.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Poi width utils.
 * 自适应列宽度工具类
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-09-06 16:51:49
 */
public class POIWidthUtils {

    public static final Pattern CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");

    private static class WidthDescriptor {
        int length;
        int chineseCount;
        int byteLength;

        public void determineChineseCount(String value) {
            if(StringUtils.isNotBlank(value)) {
                Matcher matcher = CHINESE_PATTERN.matcher(value);
                int i = 0;
                while(matcher.find()) {
                    ++i;
                }
                this.chineseCount = i;
            }
        }

        public void setByteLength(String value) {
            this.byteLength = value.getBytes().length;
        }

        public int getFinalLength() {
            if(length == chineseCount) {
                return (int)(this.byteLength * 256 * 1.1d);
            }
            return (int) ((this.byteLength - this.chineseCount) * 256 * 1.3d);
        }
    }

    /**
     * Auto size width.
     *
     * @param workbook the workbook
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 16:51:49
     */
    public static void autoSizeWidth(Sheet sheet) {
        if(sheet == null) {
            return;
        }
        int rowCount = sheet.getPhysicalNumberOfRows();
        if(rowCount == 0) {
            return;
        }
        Map<Integer, WidthDescriptor> columnMaxStrLength = new HashMap<Integer, WidthDescriptor>();
        for(Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext(); ) {
            Row row = iterator.next();
            if(row == null) {
                continue;
            }
            for(int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                Cell cell = row.getCell(j);
                if(cell == null) {
                    continue;
                }
                String value = getFormatCellValue(cell);
                int length = value.length();
                if(columnMaxStrLength.containsKey(j)) {
                    WidthDescriptor w = columnMaxStrLength.get(j);
                    if(length > w.length) {
                        w.length = length;
                        w.determineChineseCount(value);
                        w.setByteLength(value);
                    }
                } else {
                    WidthDescriptor w = new WidthDescriptor();
                    w.length = length;
                    w.determineChineseCount(value);
                    w.setByteLength(value);
                    columnMaxStrLength.put(j, w);
                }
            }
        }
        for(Map.Entry<Integer, WidthDescriptor> entry : columnMaxStrLength.entrySet()) {
            sheet.setColumnWidth(entry.getKey(), entry.getValue().getFinalLength());
        }
    }

    /**
     * Gets format cell value.
     *
     * @param cell the cell
     * @return the format cell value
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:28
     */
    public static String getFormatCellValue(Cell cell) {
        if(cell == null) {
            return org.apache.commons.lang3.StringUtils.EMPTY;
        }
        switch(cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                return StringUtils.EMPTY;
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            default:
                return StringUtils.EMPTY;
        }
    }
}
