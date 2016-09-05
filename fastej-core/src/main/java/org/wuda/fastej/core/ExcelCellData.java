package org.wuda.fastej.core;

/**
 * The type Excel cell data.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:18
 */
public class ExcelCellData {
    /**
     * The Field name.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:18
     */
    private final String fieldName;
    /**
     * The Field info.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:18
     */
    private final ExcelBaseFieldInfo fieldInfo;
    /**
     * The Cell data.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:18
     */
    private final String cellData;

    /**
     * Instantiates a new Excel cell data.
     *
     * @param fieldName the field name
     * @param fieldInfo the field info
     * @param cellData  the cell data
     */
    public ExcelCellData(String fieldName, ExcelBaseFieldInfo fieldInfo, String cellData) {
        this.fieldName = fieldName;
        this.fieldInfo = fieldInfo;
        this.cellData = cellData;
    }

    /**
     * Gets field name.
     *
     * @return the field name
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:18
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Gets field info.
     *
     * @return the field info
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:18
     */
    public ExcelBaseFieldInfo getFieldInfo() {
        return fieldInfo;
    }

    /**
     * Gets cell data.
     *
     * @return the cell data
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:18
     */
    public String getCellData() {
        return cellData;
    }
}
