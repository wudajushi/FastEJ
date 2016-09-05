package org.wuda.fastej.test;

import org.junit.Test;

/**
 * Created by dell on 2016/8/4.
 */
public class TestReflection {
    @Test
    public void testFields(){
        System.out.println(TestBean.class.getFields().length);
    }
}
