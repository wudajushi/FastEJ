package org.wuda.fastej.sax;

import org.wuda.fastej.core.EJReadExcelException;
import org.wuda.fastej.core.ExcelRawData;
import org.wuda.fastej.annotation.ExcelType;
import org.wuda.fastej.util.Assert;
import org.wuda.fastej.util.POIWidthUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Excel raw data reader.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:28
 */
public class ExcelRawDataReader {
    /**
     * The constant logger.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:28
     */
    private static final Logger logger = LoggerFactory.getLogger(ExcelRawDataReader.class);

    /**
     * Read excel raw data.
     *
     * @param inputStream the input stream
     * @param excelType   the excel type
     * @return the excel raw data
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:28
     */
    public static ExcelRawData read(InputStream inputStream, final ExcelType excelType) throws IOException {
        return read(inputStream, excelType, null, 0);
    }

    public static ExcelRawData read(InputStream inputStream, final ExcelType excelType, int[] sheetIdxs, int headerRowIndex) throws IOException {
        Assert.notNull(inputStream, "InputStream must not be null !");
        Assert.notNull(excelType, "ExcelType must not be null !");
        if(excelType == ExcelType.HSSF) {
            Workbook workbook = new HSSFWorkbook(inputStream);
            if(sheetIdxs == null) {
                sheetIdxs = new int[workbook.getNumberOfSheets()];
                for(int i = 0; i < sheetIdxs.length; ++i) {
                    sheetIdxs[i] = i;
                }
            }
            List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
            Sheet sheet = workbook.getSheetAt(0);
            int firstRowNum = sheet.getFirstRowNum();
            if(headerRowIndex != -1) {
                firstRowNum = headerRowIndex;
            }
            Map<Integer, String> indexToColumnName = new HashMap<Integer, String>();
            Row header = sheet.getRow(firstRowNum);
            int cellCount = header.getPhysicalNumberOfCells();
            Map<Integer, String> headerMap = new HashMap<Integer, String>();
            for(int i = 0; i < cellCount; ++i) {
                Cell cell = header.getCell(i);
                int index = cell.getColumnIndex();
                String value = POIWidthUtils.getFormatCellValue(cell);
                headerMap.put(index, value);
            }
            for(int i : sheetIdxs) {
                if(i < 1) {
                    logger.error("sheetIndex must not less than one !");
                    continue;
                }
                Sheet iterateSheet = workbook.getSheetAt(i);
                if(iterateSheet == null) {
                    logger.error("Sheet[index = {}] is empty !", i);
                    continue;
                }
                int rowCount = iterateSheet.getPhysicalNumberOfRows();
                for(int j = 1; j < rowCount; ++j) {
                    Row rowItem = sheet.getRow(j);
                    int rowItemCellCount = rowItem.getPhysicalNumberOfCells();
                    Map<String, String> rowDatas = new HashMap<String, String>();
                    for(int k = 0; j < rowItemCellCount; ++k) {
                        rowDatas.put(headerMap.get(k), POIWidthUtils.getFormatCellValue(rowItem.getCell(k)));
                    }
                    datas.add(rowDatas);
                }
            }
            return new ExcelRawData(datas, headerMap, excelType);
        } else {
            if(headerRowIndex <= -1) {
                headerRowIndex = 0;
            }
            DeserializerRowProcessor rowProcessor = new DeserializerRowProcessor(headerRowIndex);
            ExcelXSSFSaxReader reader = new ExcelXSSFSaxReader(rowProcessor);
            if(ArrayUtils.isEmpty(sheetIdxs)) {
                try {
                    reader.processAllSheets(inputStream);
                } catch(Exception e) {
                    logger.error("reader excel xssf error !", e);
                    throw new EJReadExcelException("reader excel xssf error !", e);
                }
            } else {
                try {
                    reader.processSpittingSheets(inputStream, sheetIdxs);
                } catch(Exception e) {
                    logger.error("reader excel xssf error !", e);
                    throw new EJReadExcelException("reader excel xssf error !", e);
                }
            }
            if(!rowProcessor.isFinished()) {
                throw new IllegalStateException("Unexpected error ,document may be finished !");
            }
            return new ExcelRawData(rowProcessor.getDatas(), rowProcessor.getHeaderMap(), excelType);
        }
    }


}
