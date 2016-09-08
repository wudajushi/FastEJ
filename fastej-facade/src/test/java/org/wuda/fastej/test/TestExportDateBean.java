package org.wuda.fastej.test;

import org.wuda.fastej.annotation.EJValidationMessageKey;
import org.wuda.fastej.annotation.ExcelBean;
import org.wuda.fastej.annotation.ExcelField;
import org.wuda.fastej.annotation.ExcelType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Test 20 w.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-27 09:27:53
 */
@ExcelBean(inputType = ExcelType.XSSF, outputType = ExcelType.XSSF)
@EJValidationMessageKey(messageKey = "id")
public class TestExportDateBean {
    /**
     * The Date.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:50:51
     */
    @ExcelField(columnName = "测试日期", index = 0, datePattern = "yyyy年MM月dd日")
    private Date date;
    /**
     * The Date 2.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:50:51
     */
    @ExcelField(columnName = "测试日期2", index = 1)
    private Date date2;

    /**
     * Gets date 2.
     *
     * @return the date 2
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:50:51
     */
    public Date getDate2() {
        return date2;
    }

    /**
     * Sets date 2.
     *
     * @param date2 the date 2
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:50:51
     */
    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    /**
     * Gets date.
     *
     * @return the date
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:50:51
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:50:51
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public static List<TestExportDateBean> genRandom(int size) {
        List<TestExportDateBean> testExportDates = new ArrayList<TestExportDateBean>();
        for(int i = 0; i < size; ++i) {
            TestExportDateBean bean = new TestExportDateBean();
            bean.setDate(new Date());
            bean.setDate2(new Date());
            testExportDates.add(bean);
        }
        return testExportDates;
    }
}
