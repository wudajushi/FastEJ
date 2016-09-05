package org.wuda.fastej.test;

import org.junit.Test;
import org.wuda.fastej.Excel;
import org.wuda.fastej.annotation.ExcelType;
import org.wuda.fastej.core.ExcelClassInfo;
import org.wuda.fastej.core.ExcelClassInfoReader;
import org.wuda.fastej.core.ExcelRawData;
import org.wuda.fastej.deserializer.BeanDeserializer;
import org.wuda.fastej.deserializer.JavaBeanDeserializer;
import org.wuda.fastej.sax.DeserializerRowProcessor;
import org.wuda.fastej.sax.ExcelRawDataReader;
import org.wuda.fastej.sax.ExcelXSSFSaxReader;
import org.wuda.fastej.test.poi.ExampleEventUserModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * The type Test performance.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-27 09:27:40
 */
public class TestPerformance {
    /**
     * The constant beanDeserializer.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:40
     */
    private static BeanDeserializer beanDeserializer = new JavaBeanDeserializer();
    private static String path = TestSpittingPerformance.class.getClassLoader().getResource("./").getPath();

    /**
     * Test performance.
     *
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:40
     */
    @Test
    public void testPerformance() throws IOException {
        long time = System.currentTimeMillis();
        ExcelClassInfo classInfo = ExcelClassInfoReader.readExcelClassInfo(TestBean.class);
        ExcelRawData rawData = ExcelRawDataReader.read(new FileInputStream(path + "5000.xlsx"), classInfo.getInputType());
        for(int i = 0; i < 10000000; i++) {
            beanDeserializer.deserialize(rawData, classInfo);
        }
        long spent = System.currentTimeMillis() - time;
        System.out.println("Spent " + spent + " ms");
    }

    /**
     * Test read raw data.
     *
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:40
     */
    @Test
    public void testReadRawData() throws IOException {
        long time = System.currentTimeMillis();
        ExcelRawData rawData = ExcelRawDataReader.read(new FileInputStream(path + "1W.xlsx"), ExcelType.XSSF);
        System.out.println("Spent " + (System.currentTimeMillis() - time) + "ms");
    }

    /**
     * Test 20 w.
     *
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:40
     */
    @Test
    public void test20W() throws IOException {
        long time = System.currentTimeMillis();
        ExcelRawData rawData = ExcelRawDataReader.read(new FileInputStream(path + "20W.xlsx"), ExcelType.XSSF);
        System.out.println("Spent " + (System.currentTimeMillis() - time) + "ms");
    }

    /**
     * Test 20 w xls.
     *
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:40
     */
    @Test
    public void test20WXls() throws IOException {
        long time = System.currentTimeMillis();
        ExcelRawData rawData = ExcelRawDataReader.read(new FileInputStream(path + "20W.xls"), ExcelType.HSSF);
        System.out.println("Spent " + (System.currentTimeMillis() - time) + "ms");
    }

    /**
     * Test sax.
     *
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:40
     */
    @Test
    public void testSax() throws Exception {
        long time = System.currentTimeMillis();
        ExcelXSSFSaxReader reader = new ExcelXSSFSaxReader(new DeserializerRowProcessor(0));
        reader.processOneSheet(path + "20W.xlsx");
        System.out.println("Spent " + (System.currentTimeMillis() - time) + "ms");
    }

    /**
     * Test example.
     *
     * @throws Exception the exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:40
     */
    @Test
    public void testExample() throws Exception {
        long time = System.currentTimeMillis();
        ExampleEventUserModel u = new ExampleEventUserModel();
        u.processOneSheet(path + "20W.xlsx");
        System.out.println("Spent " + (System.currentTimeMillis() - time) + "ms");

    }

    @Test
    public void testReadAndParse() throws IOException {
        long time = System.currentTimeMillis();
        List<Test20W> resultList = Excel.toJavaBean(path + "5000.xlsx", Test20W.class);
        System.out.println("Spent " + (System.currentTimeMillis() - time) + "ms");
        System.out.println("hello,world");
    }
}
