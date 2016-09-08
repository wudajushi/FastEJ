package org.wuda.fastej.test;

import org.wuda.fastej.core.ExcelClassInfo;
import org.wuda.fastej.core.ExcelClassInfoReader;
import junit.framework.TestCase;

public class TestGetter extends TestCase {
    public void testGetter() {
        ExcelClassInfo classInfo = ExcelClassInfoReader.readExcelClassInfo(Test20W.class);
        System.out.println(classInfo);
    }
}
