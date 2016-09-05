package org.wuda.fastej.core;

/**
 * The type Export configuration.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-08-26 17:17:21
 */
public class ExportConfiguration {
    /**
     * The One sheet max num.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:17:21
     */
    private int oneSheetMaxNum = 1000000;
    /**
     * The Sheet names.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:17:21
     */
    private String[] sheetNames;

    /**
     * The Cell style creator.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:34:51
     */
    private CellStyleCreator cellStyleCreator = DefaultCellStyleCreator.INSTANCE;

    /**
     * Gets one sheet max num.
     *
     * @return the one sheet max num
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:17:21
     */
    public int getOneSheetMaxNum() {
        return oneSheetMaxNum;
    }

    /**
     * Sets one sheet max num.
     *
     * @param oneSheetMaxNum the one sheet max num
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:17:21
     */
    public void setOneSheetMaxNum(int oneSheetMaxNum) {
        this.oneSheetMaxNum = oneSheetMaxNum;
    }

    /**
     * Get sheet names string [ ].
     *
     * @return the string [ ]
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:17:21
     */
    public String[] getSheetNames() {
        return sheetNames;
    }

    /**
     * Sets sheet names.
     *
     * @param sheetNames the sheet names
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:17:21
     */
    public void setSheetNames(String[] sheetNames) {
        this.sheetNames = sheetNames;
    }

    /**
     * Gets cell style creator.
     *
     * @return the cell style creator
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:58:09
     */
    public CellStyleCreator getCellStyleCreator() {
        return cellStyleCreator;
    }

    /**
     * Sets cell style creator.
     *
     * @param cellStyleCreator the cell style creator
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 17:58:09
     */
    public void setCellStyleCreator(CellStyleCreator cellStyleCreator) {
        this.cellStyleCreator = cellStyleCreator;
    }
}
