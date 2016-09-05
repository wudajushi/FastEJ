package org.wuda.fastej.core;

import org.wuda.fastej.annotation.ExcelType;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The type Excel raw data.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:26
 */
public class ExcelRawData {
    /**
     * The Datas.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:26
     */
    private final List<Map<String, String>> datas;
    /**
     * The Header.
     * Integer: index
     * String: columnName
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:26
     */
    private final Map<Integer, String> header;
    /**
     * The Excel type.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:26
     */
    private final ExcelType excelType;

    /**
     * Instantiates a new Excel raw data.
     *
     * @param datas     the datas
     * @param header    the header
     * @param excelType the excel type
     */
    public ExcelRawData(List<Map<String, String>> datas, Map<Integer, String> header, ExcelType excelType) {
        this.datas = datas;
        this.header = header;
        this.excelType = excelType;
    }

    /**
     * Gets datas.
     *
     * @return the datas
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:26
     */
    public List<Map<String, String>> getDatas() {
        return datas == null ? null : Collections.unmodifiableList(datas);
    }

    /**
     * Gets excel type.
     *
     * @return the excel type
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:26
     */
    public ExcelType getExcelType() {
        return excelType;
    }

    /**
     * Gets header.
     *
     * @return the header
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:26
     */
    public Map<Integer, String> getHeader() {
        return header == null ? null : Collections.unmodifiableMap(header);
    }
}
