package org.wuda.fastej.test;

import org.wuda.fastej.annotation.EJValidationMessageKey;
import org.wuda.fastej.annotation.ExcelBean;
import org.wuda.fastej.annotation.ExcelField;
import org.wuda.fastej.annotation.ExcelType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

/**
 * The type Test 20 w.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-27 09:27:53
 */
@ExcelBean(inputType = ExcelType.XSSF, outputType = ExcelType.XSSF)
@EJValidationMessageKey(messageKey = "id")
public class Test20W {
    /**
     * The Id.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "主键", datePattern = "id")
    @Min(10)
    private int id;
    /**
     * The Code.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "编码", datePattern = "code")
    @Length(min = 0, max = 15)
    private String code;
    /**
     * The Num.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "编号", index = -1, datePattern = "num")
    private String num;
    /**
     * The Directory.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "目录", datePattern = "directory")
    private String directory;
    /**
     * The Name.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "名称", datePattern = "name")
    private String name;
    /**
     * The Measure.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "计量", datePattern = "measure")
    private String measure;
    /**
     * The Art no.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "货号", datePattern = "artNo")
    private String artNo;
    /**
     * The Flag.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "标识位", datePattern = "flag")
    private String flag;
    /**
     * The Store location.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "库位", datePattern = "storeLocation")
    private String storeLocation;
    /**
     * The Create date.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "创建时间", datePattern = "createDate")
    private String createDate;
    /**
     * The Primary code.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "主要编码", datePattern = "primaryCode")
    private String primaryCode;
    /**
     * The Factory no.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "厂家编号", datePattern = "factoryNo")
    private String factoryNo;
    /**
     * The Unit.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    @ExcelField(columnName = "单位", datePattern = "unit")
    private String unit;

    /**
     * Gets art no.
     *
     * @return the art no
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public String getArtNo() {
        return artNo;
    }

    /**
     * Sets art no.
     *
     * @param artNo the art no
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public void setArtNo(String artNo) {
        this.artNo = artNo;
    }

    /**
     * Gets code.
     *
     * @return the code
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets create date.
     *
     * @return the create date
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets directory.
     *
     * @return the directory
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Sets directory.
     *
     * @param directory the directory
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * Gets factory no.
     *
     * @return the factory no
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public String getFactoryNo() {
        return factoryNo;
    }

    /**
     * Sets factory no.
     *
     * @param factoryNo the factory no
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public void setFactoryNo(String factoryNo) {
        this.factoryNo = factoryNo;
    }

    /**
     * Gets flag.
     *
     * @return the flag
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Sets flag.
     *
     * @param flag the flag
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:53
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * Gets measure.
     *
     * @return the measure
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * Sets measure.
     *
     * @param measure the measure
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /**
     * Gets name.
     *
     * @return the name
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets num.
     *
     * @return the num
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public String getNum() {
        return num;
    }

    /**
     * Sets num.
     *
     * @param num the num
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * Gets primary code.
     *
     * @return the primary code
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public String getPrimaryCode() {
        return primaryCode;
    }

    /**
     * Sets primary code.
     *
     * @param primaryCode the primary code
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }

    /**
     * Gets store location.
     *
     * @return the store location
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public String getStoreLocation() {
        return storeLocation;
    }

    /**
     * Sets store location.
     *
     * @param storeLocation the store location
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public String getUnit() {
        return unit;
    }

    @ExcelField(columnName = "自定义Getter", isGetter = true, index = 1)
    public String getCustomName() {
        return "你好";
    }

    /**
     * Sets unit.
     *
     * @param unit the unit
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:54
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Test20W{" +
                "artNo='" + artNo + '\'' +
                ", id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", num='" + num + '\'' +
                ", directory='" + directory + '\'' +
                ", name='" + name + '\'' +
                ", measure='" + measure + '\'' +
                ", flag='" + flag + '\'' +
                ", storeLocation='" + storeLocation + '\'' +
                ", createDate='" + createDate + '\'' +
                ", primaryCode='" + primaryCode + '\'' +
                ", factoryNo='" + factoryNo + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
