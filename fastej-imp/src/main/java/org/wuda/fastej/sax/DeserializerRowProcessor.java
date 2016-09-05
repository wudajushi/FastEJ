package org.wuda.fastej.sax;

import org.wuda.fastej.util.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一个Sax读取Excel的处理器
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-26 16:41:06
 */
public class DeserializerRowProcessor implements SaxRowProcessor<Void> {
    /**
     * The Header map.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:41:06
     */
    private final Map<Integer, String> headerMap = new HashMap<Integer, String>();
    /**
     * The Datas.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:41:06
     */
    private final List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
    /**
     * The Header row index.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:41:06
     */
    private final int headerRowIndex;
    /**
     * The constant logger.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:41:06
     */
    private final static Logger logger = LoggerFactory.getLogger(DeserializerRowProcessor.class);
    /**
     * The Is finished.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:41:06
     */
    private boolean isFinished;

    public Void processRow(int sheetIndex, int rowIndex, List<String> cellValues) {
        if(CollectionUtils.isEmpty(cellValues)) {
            logger.error("sheetIndex[{}] , rowIndex[{}] ,cellValues is empty !", sheetIndex, rowIndex);
            return null;
        }
        if(rowIndex == headerRowIndex) {
            for(int i = 0; i < cellValues.size(); ++i) {
                String headerValue = cellValues.get(i);
                if(StringUtils.isBlank(headerValue)) {
                    logger.error("sheetIndex[{}], headerRow[index = {}], cell[{}] is blank!", sheetIndex, headerRowIndex, i);
                    continue;
                }
                headerMap.put(i, headerValue.trim());
            }
        } else {
            Map<String, String> rowMap = new HashMap<String, String>();
            for(int i = 0; i < cellValues.size(); ++i) {
                String value = cellValues.get(i);
                if(StringUtils.isBlank(value)) {
                    logger.error("sheetIndex[{}], row[index = {}], cell[{}] is blank!", sheetIndex, headerRowIndex, i);
                    continue;
                }
                rowMap.put(headerMap.get(i), value.trim());
            }
            datas.add(rowMap);
        }

        return null;
    }

    public void processDocumentEnd() {
        this.isFinished = true;
    }

    /**
     * Instantiates a new Deserializer row processor.
     *
     * @param headerRowIndex the header row index
     */
    public DeserializerRowProcessor(int headerRowIndex) {
        this.headerRowIndex = headerRowIndex;
    }

    /**
     * Is finished boolean.
     *
     * @return the boolean
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:41:06
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Sets finished.
     *
     * @param finished the finished
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:41:06
     */
    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    /**
     * Gets datas.
     *
     * @return the datas
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:41:06
     */
    public List<Map<String, String>> getDatas() {
        return datas;
    }


    /**
     * Gets header map.
     *
     * @return the header map
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:41:06
     */
    public Map<Integer, String> getHeaderMap() {
        return headerMap;
    }


    /**
     * Gets header row index.
     *
     * @return the header row index
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-26 16:41:06
     */
    public int getHeaderRowIndex() {
        return headerRowIndex;
    }

}
