package org.wuda.fastej.core;

import java.util.List;
import java.util.Map;

/**
 * The type Excel bean binder data.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:13
 */
public class ExcelBeanBinderData {
    /**
     * The All cell datas.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:13
     */
    private final List<Map<String, ExcelCellData>> allCellDatas;
    /**
     * The Excel class info.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:13
     */
    private final ExcelClassInfo excelClassInfo;

    /**
     * Instantiates a new Excel bean binder data.
     *
     * @param allCellDatas   the all cell datas
     * @param excelClassInfo the excel class info
     */
    public ExcelBeanBinderData(List<Map<String, ExcelCellData>> allCellDatas, ExcelClassInfo excelClassInfo) {
        this.allCellDatas = allCellDatas;
        this.excelClassInfo = excelClassInfo;
    }

    /**
     * Gets all cell datas.
     *
     * @return the all cell datas
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:13
     */
    public List<Map<String, ExcelCellData>> getAllCellDatas() {
        return allCellDatas;
    }

    /**
     * Gets excel class info.
     *
     * @return the excel class info
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:13
     */
    public ExcelClassInfo getExcelClassInfo() {
        return excelClassInfo;
    }
}
