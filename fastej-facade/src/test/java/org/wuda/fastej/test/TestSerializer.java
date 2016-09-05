package org.wuda.fastej.test;

import org.wuda.fastej.Excel;
import org.wuda.fastej.core.ExcelClassInfoReader;
import org.wuda.fastej.core.ExportConfiguration;
import org.wuda.fastej.serializer.BeanSerializer;
import org.wuda.fastej.serializer.JavaBeanSerializer;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by dell on 2016/8/3.
 */
public class TestSerializer {
    private static final BeanSerializer beanSerializer = new JavaBeanSerializer();
    private static final String generatePath = "E:\\fastejtest.xlsx";

    @Test
    public void testMergeCell() throws IOException {
        List<TestNestedBean> list = TestNestedResolver.genRandom(TestNestedBean.class);
        Workbook workbook = beanSerializer.serializer(list, ExcelClassInfoReader.readExcelClassInfo(TestNestedBean
                .class), new ExportConfiguration());
        workbook.write(new FileOutputStream("manual_" + generatePath));
    }

    @Test
    public void testFullFlow() throws IOException {
        List<TestNestedBean> list = TestNestedResolver.genRandom(TestNestedBean.class);
        Workbook workbook = Excel.fromJavaBean(list, TestNestedBean.class);
        workbook.write(new FileOutputStream(generatePath));
    }
}
