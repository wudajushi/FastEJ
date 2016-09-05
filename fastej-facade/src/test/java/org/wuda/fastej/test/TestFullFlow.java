package org.wuda.fastej.test;

import org.junit.Test;
import org.wuda.fastej.Excel;

import java.io.IOException;
import java.util.List;

/**
 * The type Test full flow.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-27 09:27:48
 */
public class TestFullFlow {
    /**
     * Test full flow.
     *
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:48
     */
    @Test
    public void testFullFlow() throws IOException {
        List<TestBean> beans = Excel.toJavaBean("D:\\asmwork\\1.xlsx", TestBean.class);
        System.out.println(beans);
    }
}
