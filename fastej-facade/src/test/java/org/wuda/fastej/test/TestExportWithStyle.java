package org.wuda.fastej.test;

import org.wuda.fastej.Excel;
import junit.framework.TestCase;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * The type Test export with style.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-08-26 18:02:51
 */
public class TestExportWithStyle extends TestCase {
    private static String path = TestExportWithStyle.class.getClassLoader().getResource("./").getPath();

    /**
     * Test export with style.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-26 18:02:51
     */
    public void testExportWithStyle() throws IOException {
        List<Test20W> list = Excel.toJavaBean(path + "5000.xlsx", Test20W.class);
        Workbook workbook = Excel.fromJavaBean(list, Test20W.class);
        workbook.write(new FileOutputStream("D:\\5000withstyle.xlsx"));
    }
}
