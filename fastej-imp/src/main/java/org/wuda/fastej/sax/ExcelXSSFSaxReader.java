package org.wuda.fastej.sax;

import org.wuda.fastej.util.Assert;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Excel XSSF (Excel2007) Sax读取器<br/>
 * <b>需要注意的是这个类是线程不安全的。<b><br/>
 * <b>Note : this class is not thread-safe .</b>
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-26 16:46:05
 * @see : <a href="http://poi.apache.org/spreadsheet/how-to.html#xssf_sax_api">官方例子</a>
 */
public class ExcelXSSFSaxReader {
    /**
     * The Next is string.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:46:05
     */
    private boolean nextIsString;
    /**
     * The Last contents.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:46:05
     */
    private String lastContents;
    /**
     * The Sheet index.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:46:05
     */
    private int sheetIndex;
    /**
     * The Data flag.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:46:05
     */
    private boolean dataFlag;
    /**
     * The constant logger.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:46:05
     */
    private static final Logger logger = LoggerFactory.getLogger(ExcelXSSFSaxReader.class);
    /**
     * The Row index.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:01:28
     */
    private int rowIndex;
    /**
     * The Col index.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:01:28
     */
    private int colIndex;
    /**
     * The Cell values.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:01:28
     */
    private List<String> cellValues = new ArrayList<String>();
    /**
     * 行处理器
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:01:28
     */
    private SaxRowProcessor saxRowProcessor;

    /**
     * Process one sheet.
     *
     * @param inputStream the input stream
     * @param sheetId     the sheet id
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:25:25
     */
    public void processOneSheet(InputStream inputStream, int sheetId) throws Exception {
        OPCPackage pkg = OPCPackage.open(inputStream);
        processOneSheet(pkg, sheetId);
    }

    /**
     * Process one sheet.
     *
     * @param inputStream the input stream
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:25:25
     */
    public void processOneSheet(InputStream inputStream) throws Exception {
        processOneSheet(inputStream, 1);
    }

    /**
     * Process one sheet.
     *
     * @param pkg     the pkg
     * @param sheetId the sheet id
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:25:25
     */
    private void processOneSheet(OPCPackage pkg, int sheetId) throws Exception {
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();

        XMLReader parser = fetchSheetParser(sst);

        InputStream sheet = r.getSheet("rId" + sheetId);
        InputSource sheetSource = new InputSource(sheet);
        parser.parse(sheetSource);
        sheet.close();
    }

    /**
     * Process one sheet.
     *
     * @param filename the filename
     * @param sheetId  the sheet id
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:46:05
     */
    public void processOneSheet(String filename, int sheetId) throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        processOneSheet(pkg, sheetId);
    }

    /**
     * Process one sheet.
     *
     * @param filename the filename
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:25:25
     */
    public void processOneSheet(String filename) throws Exception {
        processOneSheet(filename, 1);
    }

    /**
     * Instantiates a new Excel xssf sax reader.
     *
     * @param saxRowProcessor the sax row processor
     */
    public ExcelXSSFSaxReader(SaxRowProcessor saxRowProcessor) {
        Assert.notNull(saxRowProcessor, "SaxRowProcessor must not be null !");
        this.saxRowProcessor = saxRowProcessor;
    }

    /**
     * Instantiates a new Excel xssf sax reader.
     */
    public ExcelXSSFSaxReader() {

    }

    /**
     * Gets sax row processor.
     *
     * @return the sax row processor
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:25:25
     */
    public SaxRowProcessor getSaxRowProcessor() {
        return saxRowProcessor;
    }

    /**
     * Sets sax row processor.
     *
     * @param saxRowProcessor the sax row processor
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:25:25
     */
    public void setSaxRowProcessor(SaxRowProcessor saxRowProcessor) {
        Assert.notNull(saxRowProcessor, "SaxRowProcessor must not be null !");
        this.saxRowProcessor = saxRowProcessor;
    }

    /**
     * Process all sheets.
     *
     * @param filename the filename
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:46:05
     */
    public void processAllSheets(String filename) throws Exception {
        OPCPackage pkg = OPCPackage.open(filename);
        processAllSheets(pkg);
    }

    /**
     * Process all sheets.
     *
     * @param inputStream the input stream
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:25:25
     */
    public void processAllSheets(InputStream inputStream) throws Exception {
        OPCPackage pkg = OPCPackage.open(inputStream);
        processAllSheets(pkg);
    }

    /**
     * 处理分散的Sheet
     *
     * @param inputStream the input stream
     * @param sheetIdxs   the sheet idxs
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:46:10
     */
    public void processSpittingSheets(InputStream inputStream, int[] sheetIdxs) throws Exception {
        Assert.notEmpty(sheetIdxs, "SheetIdxs must not be empty !");
        OPCPackage pkg = OPCPackage.open(inputStream);
        processSpittingSheets(pkg, sheetIdxs);
    }

    /**
     * 处理分散的Sheet
     *
     * @param filename  the filename
     * @param sheetIdxs the sheet idxs
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:46:10
     */
    public void processSpittingSheets(String filename, int[] sheetIdxs) throws Exception {
        Assert.notEmpty(sheetIdxs, "SheetIdxs must not be empty !");
        OPCPackage pkg = OPCPackage.open(filename);
        processSpittingSheets(pkg, sheetIdxs);
    }

    /**
     * Process spitting sheets.
     *
     * @param pkg       the pkg
     * @param sheetIdxs the sheet idxs
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:46:10
     */
    private void processSpittingSheets(OPCPackage pkg, int[] sheetIdxs) throws Exception {
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();

        XMLReader parser = fetchSheetParser(sst);
        for(int i : sheetIdxs) {
            InputStream sheet = r.getSheet("rId" + i);
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
        }
    }

    /**
     * Process all sheets.
     *
     * @param pkg the pkg
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 17:25:25
     */
    private void processAllSheets(OPCPackage pkg) throws Exception {
        XSSFReader r = new XSSFReader(pkg);
        SharedStringsTable sst = r.getSharedStringsTable();

        XMLReader parser = fetchSheetParser(sst);

        Iterator<InputStream> sheets = r.getSheetsData();
        while(sheets.hasNext()) {
            logger.debug("Processing new sheet:\n");
            InputStream sheet = sheets.next();
            InputSource sheetSource = new InputSource(sheet);
            parser.parse(sheetSource);
            sheet.close();
            rowIndex = 0;
            ++sheetIndex;
        }
    }

    /**
     * Fetch sheet parser xml reader.
     *
     * @param sst the sst
     * @return the xml reader
     * @throws SAXException the sax exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:46:05
     */
    public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
        XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
        ContentHandler handler = new ExcelXSSFSaxHandler(sst);
        parser.setContentHandler(handler);
        return parser;
    }

    /**
     * The type Excel xssf sax handler.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:46:05
     */
    private class ExcelXSSFSaxHandler extends DefaultHandler {
        /**
         * The Sst.
         *
         * @author :<a href="mailto:450783043@qq.com">悟达</a>
         * @date :2016-07-26 17:25:26
         */
        private SharedStringsTable sst;

        /**
         * The This column.
         *
         * @author :<a href="mailto:450783043@qq.com">悟达</a>
         * @date :2016-09-06 18:48:03
         */
        private int thisColumn = -1;
        /**
         * The Last column.
         *
         * @author :<a href="mailto:450783043@qq.com">悟达</a>
         * @date :2016-09-06 18:48:03
         */
        private int preColumn = -1;

        /**
         * Instantiates a new Excel xssf sax handler.
         *
         * @param sst the sst
         */
        public ExcelXSSFSaxHandler(SharedStringsTable sst) {
            this.sst = sst;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws
                SAXException {
            super.startElement(uri, localName, qName, attributes);
            if("c".equals(qName)) {
                String cellType = attributes.getValue("t");
                String ref = attributes.getValue("r");
                if(preColumn == -1) {
                    preColumn = nameToColumn(ref.replaceAll("\\d+", ""));
                } else {
                    preColumn = thisColumn;
                }
                thisColumn = nameToColumn(ref.replaceAll("\\d+", ""));
                nextIsString = "s".equals(cellType);
                String dateType = attributes.getValue("s");
                dataFlag = "1".equals(dateType);
            }
            lastContents = "";
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(nextIsString) {
                int idx = Integer.parseInt(lastContents);
                lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
                nextIsString = false;
            }
            if("v".equals(qName)) {
                String value = lastContents.trim();
                int distance = thisColumn - preColumn - 1;
                if(distance > 0) {
                    for(int i = 0; i < distance; ++i) {
                        cellValues.add(colIndex + i, "");
                    }
                    colIndex += distance;
                }
                cellValues.add(colIndex, value);
                ++colIndex;
            } else if("row".equals(qName)) {
                if(saxRowProcessor != null) {
                    saxRowProcessor.processRow(sheetIndex, rowIndex, cellValues);
                }
                ++rowIndex;
                colIndex = 0;
                thisColumn = -1;
                preColumn = -1;
                cellValues.clear();
            }

        }


        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            lastContents += new String(ch, start, length);
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            if(saxRowProcessor != null) {
                saxRowProcessor.processDocumentEnd();
            }
        }

        /**
         * Count null cell int.
         *
         * @param name the name
         * @return the int
         * @author :<a href="mailto:450783043@qq.com">悟达</a>
         * @date :2016-09-06 18:31:07
         */
        private int nameToColumn(String name) {
            int column = -1;
            for(int i = 0; i < name.length(); ++i) {
                int c = name.charAt(i);
                column = (column + 1) * 26 + c - 'A';
            }
            return column;
        }

        /**
         * Fill char string.
         *
         * @param str   the str
         * @param len   the len
         * @param let   the let
         * @param isPre the is pre
         * @return the string
         * @author :<a href="mailto:450783043@qq.com">悟达</a>
         * @date :2016-09-06 18:31:07
         */
        private String fillChar(String str, int len, char let, boolean isPre) {
            int len_1 = str.length();
            if(len_1 < len) {
                if(isPre) {
                    for(int i = 0; i < (len - len_1); i++) {
                        str = let + str;
                    }
                } else {
                    for(int i = 0; i < (len - len_1); i++) {
                        str = str + let;
                    }
                }
            }
            return str;
        }
    }
}
