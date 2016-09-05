package org.wuda.fastej.test;

import org.wuda.fastej.annotation.ExcelBean;
import org.wuda.fastej.annotation.ExcelField;
import org.wuda.fastej.annotation.ExcelType;
import org.hibernate.validator.constraints.NotBlank;

/**
 * The type Test bean.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-27 09:27:50
 */
@ExcelBean(inputType = ExcelType.XSSF, outputType = ExcelType.XSSF)
public class TestBean {
    /**
     * The Name.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    @ExcelField(columnName = "姓名", index = 0)
    @NotBlank
    private String name;
    /**
     * The Sex.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    @ExcelField(columnName = "性别", index = 1)
    private String sex;
    /**
     * The Address.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    @ExcelField(columnName = "地址", index = 2)
    private String address;
    /**
     * The Name 1.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    @ExcelField(columnName = "姓名1", index = 0)
    private String name1;
    /**
     * The Sex 1.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    @ExcelField(columnName = "性别2", index = 1)
    private String sex1;
    /**
     * The Address 1.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    @ExcelField(columnName = "地址3", index = 2)
    private String address1;
    /**
     * The Name 2.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    @ExcelField(columnName = "姓名4", index = 0)
    private String name2;
    /**
     * The Sex 2.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    @ExcelField(columnName = "性别5", index = 0)
    private String sex2;
    /**
     * The Address 2.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    @ExcelField(columnName = "地址6", index = 0)
    private String address2;
    /**
     * The Address 3.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    @ExcelField(columnName = "姓名7", index = 0)
    private String address3;

    /**
     * Gets name.
     *
     * @return the name
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets sex.
     *
     * @return the sex
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets sex.
     *
     * @param sex the sex
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Gets address.
     *
     * @return the address
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets address 1.
     *
     * @return the address 1
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets address 1.
     *
     * @param address1 the address 1
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }


    /**
     * Gets name 1.
     *
     * @return the name 1
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public String getName1() {
        return name1;
    }

    /**
     * Sets name 1.
     *
     * @param name1 the name 1
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public void setName1(String name1) {
        this.name1 = name1;
    }

    /**
     * Gets sex 1.
     *
     * @return the sex 1
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public String getSex1() {
        return sex1;
    }

    /**
     * Sets sex 1.
     *
     * @param sex1 the sex 1
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public void setSex1(String sex1) {
        this.sex1 = sex1;
    }

    /**
     * Gets address 2.
     *
     * @return the address 2
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets address 2.
     *
     * @param address2 the address 2
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Gets address 3.
     *
     * @return the address 3
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public String getAddress3() {
        return address3;
    }

    /**
     * Sets address 3.
     *
     * @param address3 the address 3
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    /**
     * Gets name 2.
     *
     * @return the name 2
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public String getName2() {
        return name2;
    }

    /**
     * Sets name 2.
     *
     * @param name2 the name 2
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public void setName2(String name2) {
        this.name2 = name2;
    }

    /**
     * Gets sex 2.
     *
     * @return the sex 2
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public String getSex2() {
        return sex2;
    }

    /**
     * Sets sex 2.
     *
     * @param sex2 the sex 2
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:50
     */
    public void setSex2(String sex2) {
        this.sex2 = sex2;
    }
}
