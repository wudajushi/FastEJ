package org.wuda.fastej.test;

import org.wuda.fastej.Excel;

import java.io.IOException;

/**
 */
public class TestConcurrent {
    private static String path = TestSpittingPerformance.class.getClassLoader().getResource("./").getPath();

    static {
        //初始化XML工厂需要时间 JVM一次性的，启动tomcat后第一次访问需要
        try {
            Excel.toJavaBeanWithValidate(path + "5000.xlsx", Test20W.class);
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("before");
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                long time = System.currentTimeMillis();
                try {
                    Excel.toJavaBeanWithValidate(path + "5000.xlsx", Test20W.class);
                } catch(IOException e) {
                }
                System.out.println("5000数据 Spent: " + (System.currentTimeMillis() - time) + " ms");
            }
        };
        for(int i = 0; i < 100; i++) {
            new Thread(runnable).start();
        }
    }
}
