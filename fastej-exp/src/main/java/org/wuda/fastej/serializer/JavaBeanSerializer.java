package org.wuda.fastej.serializer;

import org.wuda.fastej.annotation.ExcelType;
import org.wuda.fastej.core.*;
import org.wuda.fastej.util.Assert;
import org.wuda.fastej.util.CollectionUtils;
import org.wuda.fastej.util.POIWidthUtils;
import ognl.Ognl;
import ognl.OgnlException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Java bean serializer.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:43:02
 */
public class JavaBeanSerializer implements BeanSerializer {
    /**
     * The constant logger.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-04 15:18:37
     */
    private static final Logger logger = LoggerFactory.getLogger(JavaBeanSerializer.class);
    /**
     * The constant typeConverter.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-04 17:25:02
     */
    private static final TypeConverter typeConverter = DefaultTypeConverter.INSTANCE;

    private static final CellStyleCreator cellStyleCreator = DefaultCellStyleCreator.INSTANCE;

    public <T> Workbook serializer(List<T> beanList, ExcelClassInfo excelClassInfo, ExportConfiguration configuration) {
        Assert.notEmpty(beanList, "beanList must not be empty !");
        Assert.notNull(excelClassInfo, "ExcelClassInfo must not be null !");
        Assert.notNull(configuration, "ExportConfiguration must not be null !");
        //最深的级别
        int deepestLevel = excelClassInfo.getDeepestLevel();
        Map<String, ExcelBaseFieldInfo> fieldInfos = excelClassInfo.getColumnInfo();
        Assert.notEmpty(fieldInfos, "FieldInfos must not be empty !");
        ExcelType outputType = excelClassInfo.getOutputType();
        boolean isUseSXSSF = beanList.size() > 1000;
        Workbook workbook = outputType == ExcelType.XSSF ? isUseSXSSF ? new SXSSFWorkbook(-1) : new XSSFWorkbook() :
                new HSSFWorkbook();
        Sheet[] sheets = createSheets(workbook, configuration, beanList.size());
        Map<Integer, Map<Integer, String>> ognlSheetMap = generateHeader(sheets, excelClassInfo, deepestLevel,
                configuration);
        Map<Integer, String> datePatternMap = determineColDatePattern(excelClassInfo);
        generateDatas(beanList, sheets, ognlSheetMap, datePatternMap, configuration, deepestLevel + 1);
        return workbook;
    }

    private Map<Integer, String> determineColDatePattern(ExcelClassInfo classInfo) {
        if(classInfo == null || CollectionUtils.isEmpty(classInfo.getFieldInfo())) {
            return null;
        }
        int i = 0;
        Map<Integer, String> resultMap = new HashMap<Integer, String>();
        for(ExcelBaseFieldInfo fieldInfo : classInfo.getFieldInfo().values()) {
            resultMap.put(i++, fieldInfo.getDatePattern());
        }
        System.out.println(resultMap);
        return resultMap;
    }

    /**
     * Generate datas.
     *
     * @param <T>           the type parameter
     * @param beanList      the bean list
     * @param sheets        the sheets
     * @param ognlSheetMap  the ognl sheet map
     * @param configuration the configuration
     * @param dataStartRow  the data start row
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-04 17:25:01
     */
    private <T> void generateDatas(List<T> beanList, Sheet[] sheets, Map<Integer, Map<Integer, String>> ognlSheetMap,
                                   Map<Integer, String> datePatternMap, ExportConfiguration configuration, int
                                           dataStartRow) {
        Assert.notEmpty(beanList, "beanList must not be empty !");
        Assert.notEmpty(sheets, "sheets must not be empty !");
        Assert.notEmpty(ognlSheetMap, "ognlSheetMap must not be empty !");
        Assert.notNull(configuration, "ExportConfiguration must not be null !");
        Assert.isTrue(dataStartRow > 0, "dataStartRow must not be negative !");
        int oneSheetMaxNum = configuration.getOneSheetMaxNum();
        int beanSize = beanList.size();
        if(beanSize <= oneSheetMaxNum) {
            generateOneSheetDatas(beanList, sheets[0], ognlSheetMap.get(0), datePatternMap, dataStartRow,
                    configuration);
            POIWidthUtils.autoSizeWidth(sheets[0]);
//            for(int i = 0; i < sheets[0].rowIterator().next().getPhysicalNumberOfCells(); ++i) {
//                if(sheets[0] instanceof SXSSFSheet){
//                    ((SXSSFSheet)sheets[0]).trackAllColumnsForAutoSizing();
//                }
//                sheets[0].autoSizeColumn(i, true);
//            }
        } else {
            for(int i = 0; i < sheets.length; ++i) {
                List<T> subList = beanList.subList(i * oneSheetMaxNum, (i + 1) * oneSheetMaxNum);
                generateOneSheetDatas(subList, sheets[i], ognlSheetMap.get(i), datePatternMap, dataStartRow,
                        configuration);
                POIWidthUtils.autoSizeWidth(sheets[i]);
//                for(int j = 0; j < sheets[i].rowIterator().next().getPhysicalNumberOfCells(); ++j) {
//                    if(sheets[0] instanceof SXSSFSheet){
//                        ((SXSSFSheet)sheets[i]).trackAllColumnsForAutoSizing();
//                    }
//                    sheets[i].autoSizeColumn(j, true);
//                }
            }
        }
    }

    /**
     * Generate one sheet datas.
     *
     * @param <T>          the type parameter
     * @param beanList     the bean list
     * @param oneSheet     the one sheet
     * @param ognlMap      the ognl map
     * @param dataStartRow the data start row
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-04 17:25:01
     */
    private <T> void generateOneSheetDatas(List<T> beanList, Sheet oneSheet, Map<Integer, String> ognlMap,
                                           Map<Integer, String> datePatternMap, int dataStartRow, ExportConfiguration
                                                   configuration) {
        Assert.notEmpty(beanList, "beanList must not be empty !");
        Assert.notNull(oneSheet, "Sheet must not be empty !");
        Assert.notEmpty(ognlMap, "ognlMap must not be empty");
        Assert.isTrue(dataStartRow > 0, "dataStartRow must not be negative ! ");
        System.out.println(ognlMap);
        int rowIndex = dataStartRow;
        CellStyle cellStyle;
        if(configuration != null && configuration.getCellStyleCreator() != null) {
            cellStyle = configuration.getCellStyleCreator().createDataRowStyle(oneSheet.getWorkbook(), oneSheet
                    .getSheetName(), rowIndex);
        } else {
            cellStyle = cellStyleCreator.createDataRowStyle(oneSheet.getWorkbook(), oneSheet.getSheetName(), rowIndex);
        }
        for(T bean : beanList) {
            Row row = oneSheet.createRow(rowIndex);
            for(Map.Entry<Integer, String> ognlItem : ognlMap.entrySet()) {
                Integer colIndex = ognlItem.getKey();
                String ognlExpression = ognlItem.getValue();
                Cell cell = row.createCell(colIndex);
                cell.setCellStyle(cellStyle);
                try {
                    Object value = Ognl.getValue(ognlExpression, bean);
                    if(value == null) {
                        cell.setCellValue(StringUtils.EMPTY);
                    } else if(value instanceof Date) {
                        cell.setCellValue(typeConverter.convertDateToString((Date) value, datePatternMap.get
                                (colIndex)));
                    } else {
                        cell.setCellValue(typeConverter.convert(value, String.class));
                    }
                } catch(OgnlException e) {
                    logger.error("getValue use Ognl error ! ognlExpression[{}], rowIndex[{}], colIndex[{}]",
                            ognlExpression, rowIndex, colIndex);
                    throw new EJFieldException("getValue use Ognl error ! ognlExpression[" + ognlExpression + "], " +
                            "rowIndex[" + rowIndex + "], colIndex[" + colIndex + "]", e);
                }
            }
            ++rowIndex;
        }
    }

    /**
     * Create sheets sheet [ ].
     * 创建Sheet组
     *
     * @param workbook      the workbook
     * @param configuration the configuration
     * @param beanSize      the bean size
     * @return the sheet [ ]
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-02 17:42:13
     */
    private Sheet[] createSheets(Workbook workbook, ExportConfiguration configuration, int beanSize) {
        Assert.isTrue(beanSize >= 0, "beanSize cannot be negative !");
        int oneSheetMaxNum = configuration.getOneSheetMaxNum();
        int sheetNum = beanSize % oneSheetMaxNum != 0 ? beanSize / oneSheetMaxNum + 1 : beanSize / oneSheetMaxNum;
        boolean isSplitSheet = oneSheetMaxNum > 0 && beanSize > 0;
        String[] sheetNames = configuration.getSheetNames();
        if(isSplitSheet) {
            Sheet[] sheets = new Sheet[sheetNum];
            for(int i = 0; i < sheetNum; ++i) {
                if(ArrayUtils.isNotEmpty(sheetNames)) {
                    String sheetName = sheetNames[i];
                    if(StringUtils.isNotBlank(sheetName)) {
                        try {
                            sheets[i] = workbook.createSheet(sheetName.trim());
                        } catch(Exception e) {
                            logger.error("create sheet error ! try to create sheet without special name !", e);
                            sheets[i] = workbook.createSheet();
                        }
                    } else {
                        sheets[i] = workbook.createSheet();
                    }
                } else {
                    sheets[i] = workbook.createSheet();
                }
            }
            return sheets;
        } else {
            Sheet[] sheets = new Sheet[1];
            if(ArrayUtils.isNotEmpty(sheetNames)) {
                String sheetName = sheetNames[0];
                if(StringUtils.isNotBlank(sheetName)) {
                    sheets[0] = workbook.createSheet(sheetName.trim());
                } else {
                    sheets[0] = workbook.createSheet();
                }
            } else {
                sheets[0] = workbook.createSheet();
            }
            return sheets;
        }
    }

    /**
     * 生成Header
     *
     * @param sheets         the sheets
     * @param excelClassInfo the excel class info
     * @param deepestLevel   the deepest level
     * @return the map - key:sheetIdx , value : this sheet ognl expressions map
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-04 15:18:36
     */
    private Map<Integer, Map<Integer, String>> generateHeader(Sheet[] sheets, ExcelClassInfo excelClassInfo, int
            deepestLevel, ExportConfiguration configuration) {
        Assert.notEmpty(sheets, "Sheets must not be empty !");
        Map<String, ExcelBaseFieldInfo> fieldInfos = excelClassInfo.getFieldInfo();
        int sheetIdx = 0;
        //key:sheetIdx , value : this sheet ognl expressions map
        Map<Integer, Map<Integer, String>> ognlSheetMap = new HashMap<Integer, Map<Integer, String>>();
        for(Sheet sheet : sheets) {
            int i = 0;
            Row row = sheet.createRow(0);
            //key: colIndex , value : ognl expressions
            Map<Integer, String> ognlMap = new HashMap<Integer, String>();
            ognlSheetMap.put(sheetIdx, ognlMap);
            sheetIdx++;
            for(Map.Entry<String, ExcelBaseFieldInfo> entry : fieldInfos.entrySet()) {
                ExcelBaseFieldInfo baseFieldInfo = entry.getValue();
                //递归生成表头
                recursiveGenerateHeader(sheet, row, baseFieldInfo, deepestLevel, i, 0, "", ognlMap, configuration);
                if(baseFieldInfo.isMixed()) {
                    //如果是bean（isMixed），当前列等于列数加上平摊开的列数
                    i += baseFieldInfo.getCountOfBaseField();
                } else {
                    //否则就自加
                    i++;
                }
            }
            sheet.createFreezePane(0, deepestLevel + 1, 1, 1);
        }
        return ognlSheetMap;
    }

    /**
     * Recursive generate header.
     *
     * @param sheet        the sheet
     * @param row          the row
     * @param fieldInfo    the field info
     * @param deepestLevel the deepest level
     * @param colIndex     the col index
     * @param rowIndex     the row index
     * @param expressions  the expressions
     * @param ognlMap      the ognl map
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-04 15:18:37
     */
    private void recursiveGenerateHeader(Sheet sheet, Row row, ExcelBaseFieldInfo fieldInfo, int deepestLevel, int
            colIndex, int rowIndex, String expressions, Map<Integer, String> ognlMap, ExportConfiguration
            configuration) {
        CellStyle cellStyle;
        if(configuration != null && configuration.getCellStyleCreator() != null) {
            cellStyle = configuration.getCellStyleCreator().createHeaderRowStyle(sheet.getWorkbook());
        } else {
            cellStyle = cellStyleCreator.createHeaderRowStyle(sheet.getWorkbook());
        }
        if(!fieldInfo.isMixed()) {
            Cell cell = row.createCell(colIndex);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(fieldInfo.getColumnName());
            String fieldName = fieldInfo.getFieldName();
            if(deepestLevel > rowIndex) {
                CellRangeAddress rangeAddress = new CellRangeAddress(rowIndex, deepestLevel, colIndex, colIndex);
                setHeaderBorder(rangeAddress, sheet, cellStyle);
                sheet.addMergedRegion(rangeAddress);
            }
            if(StringUtils.isNotBlank(expressions)) {
                ognlMap.put(colIndex, expressions);
            } else {
                ognlMap.put(colIndex, fieldName);
            }

        } else {
            int count = fieldInfo.getCountOfBaseField();
            Cell cell = row.createCell(colIndex);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(fieldInfo.getColumnName());
            if(count > 1) {
                CellRangeAddress rangeAddress = new CellRangeAddress(rowIndex, rowIndex, colIndex, colIndex + count -
                        1);
                setHeaderBorder(rangeAddress, sheet, cellStyle);
                sheet.addMergedRegion(rangeAddress);
            }

            Row innerRow = sheet.getRow(++rowIndex);
            if(innerRow == null) {
                innerRow = sheet.createRow(rowIndex);
            }
            String prefix = fieldInfo.getFieldName();
            for(Map.Entry<String, ExcelBaseFieldInfo> entry : fieldInfo.getChildFieldsInfo().entrySet()) {
                ExcelBaseFieldInfo innerFieldInfo = entry.getValue();
                String fieldName = innerFieldInfo.getFieldName();
                String expressionRecursive = prefix + "." + fieldName;
                if(StringUtils.isNotBlank(expressions)) {
                    expressionRecursive = expressions + "." + fieldName;
                }
                recursiveGenerateHeader(sheet, innerRow, innerFieldInfo, deepestLevel, colIndex, rowIndex,
                        expressionRecursive, ognlMap, configuration);

                colIndex += innerFieldInfo.getCountOfBaseField();
            }
        }
    }

    private void setHeaderBorder(CellRangeAddress rangeAddress, Sheet sheet, CellStyle cellStyle) {
        RegionUtil.setBorderBottom(cellStyle.getBorderBottom().getCode(), rangeAddress, sheet);
        RegionUtil.setBorderTop(cellStyle.getBorderTop().getCode(), rangeAddress, sheet);
        RegionUtil.setBorderLeft(cellStyle.getBorderLeft().getCode(), rangeAddress, sheet);
        RegionUtil.setBorderRight(cellStyle.getBorderRight().getCode(), rangeAddress, sheet);
        RegionUtil.setBottomBorderColor(cellStyle.getBottomBorderColor(), rangeAddress, sheet);
        RegionUtil.setTopBorderColor(cellStyle.getTopBorderColor(), rangeAddress, sheet);
        RegionUtil.setLeftBorderColor(cellStyle.getLeftBorderColor(), rangeAddress, sheet);
        RegionUtil.setRightBorderColor(cellStyle.getRightBorderColor(), rangeAddress, sheet);
    }
}
