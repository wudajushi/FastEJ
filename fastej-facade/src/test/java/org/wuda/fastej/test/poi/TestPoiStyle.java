package org.wuda.fastej.test.poi;

import junit.framework.TestCase;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestPoiStyle extends TestCase {
    public void testStyle() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLUE.getIndex());
        cellStyle.setTopBorderColor(IndexedColors.BLUE.getIndex());
        cellStyle.setLeftBorderColor(IndexedColors.BLUE.getIndex());
        cellStyle.setRightBorderColor(IndexedColors.BLUE.getIndex());
        Font font =  workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.DARK_GREEN.getIndex());
        cellStyle.setFont(font);
        XSSFCell cell = workbook.createSheet().createRow(0).createCell(0);
        cell.setCellValue("你好");
        cell.setCellStyle(cellStyle);
        workbook.write(new FileOutputStream("D:\\1111.xlsx"));
    }
}
