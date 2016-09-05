package org.wuda.fastej.test;

import org.wuda.fastej.Excel;
import org.wuda.fastej.validate.ValidateMessage;
import org.wuda.fastej.validate.ValidatedBean;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.Map;

/**
 * The type Test validator.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-27 09:27:43
 */
public class TestValidator extends TestCase {
    public void testValidator() throws IOException {
        long time = System.currentTimeMillis();
        Map<Object, ValidatedBean<Test20W>> result = Excel.toJavaBeanWithValidate("D:\\asmwork\\20W.xlsx", Test20W
                .class);
        System.out.println("Spent : " + (System.currentTimeMillis() - time) + " ms ");
        for(ValidatedBean<Test20W> validatedBean : result.values()) {
            System.out.println(validatedBean.isValid() ? "验证通过" : "验证不通过");
            for(ValidateMessage message : validatedBean.getValidateMessages()) {
                System.out.println(message.getMessage());
            }
        }
    }
}
