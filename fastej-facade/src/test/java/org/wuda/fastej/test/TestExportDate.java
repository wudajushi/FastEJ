package org.wuda.fastej.test;

import org.wuda.fastej.Excel;
import junit.framework.TestCase;

import java.io.FileOutputStream;
import java.io.IOException;

public class TestExportDate extends TestCase {
    public void testExportDate() throws IOException {
        Excel.fromJavaBean(TestExportDateBean.genRandom(100), TestExportDateBean.class).write(new FileOutputStream
                ("D:\\testDate.xlsx"));
    }
}
