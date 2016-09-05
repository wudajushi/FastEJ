package org.wuda.fastej.test;

import org.wuda.fastej.Excel;
import org.wuda.fastej.validate.ValidatedBean;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class TestMultipartSheet {
    private static String path = TestSpittingPerformance.class.getClassLoader().getResource("./").getPath();

    @Test
    public void testMultipartSheet() throws IOException {
        Map<Object, ValidatedBean<TestBean>> result = Excel.toJavaBeanWithValidate(path + "\\3W_3sheets.xlsx",
                TestBean.class);
        System.out.println(result);
        System.out.println(result.size());
    }

}
