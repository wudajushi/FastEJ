package org.wuda.fastej.test;

import org.wuda.fastej.Excel;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by dell on 2016/7/29.
 */
public class TestSpittingPerformance {
    private static String path = TestSpittingPerformance.class.getClassLoader().getResource("./").getPath();

    @BeforeClass
    public static void beforeClass() throws IOException {
        //初始化XML工厂需要时间 但是是JVM一次性的
        Excel.toJavaBeanWithValidate(path + "5000.xlsx", Test20W.class);
        System.out.println("before");
    }

    @Test
    public void test20W() throws IOException {
        long time = System.currentTimeMillis();
        Excel.toJavaBeanWithValidate(path + "20W.xlsx", Test20W.class);
        System.out.println("200000数据 Spent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    public void test10W() throws IOException {
        long time = System.currentTimeMillis();
        Excel.toJavaBeanWithValidate(path + "10W.xlsx", Test20W.class);
        System.out.println("100000数据 Spent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    public void test5W() throws IOException {
        long time = System.currentTimeMillis();
        Excel.toJavaBeanWithValidate(path + "5W.xlsx", Test20W.class);
        System.out.println("50000数据 Spent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    public void test1W() throws IOException {
        long time = System.currentTimeMillis();
        Excel.toJavaBeanWithValidate(path + "1W.xlsx", Test20W.class);
        System.out.println("10000数据 Spent: " + (System.currentTimeMillis() - time) + " ms");
    }

    @Test
    public void test5000() throws IOException {
        long time = System.currentTimeMillis();
        Excel.toJavaBeanWithValidate(path + "5000.xlsx", Test20W.class);
        System.out.println("5000数据 Spent: " + (System.currentTimeMillis() - time) + " ms");
    }
}
