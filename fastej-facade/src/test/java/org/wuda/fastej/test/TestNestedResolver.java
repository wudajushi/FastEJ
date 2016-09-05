package org.wuda.fastej.test;

import org.wuda.fastej.core.ExcelClassInfo;
import org.wuda.fastej.core.ExcelClassInfoReader;
import org.wuda.fastej.util.TypeUtils;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dell on 2016/8/2.
 */
public class TestNestedResolver {
    /**
     * Test nested bean.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:46
     */
    @Test
    public void testNestedBean() {
        ExcelClassInfo classInfo = ExcelClassInfoReader.readExcelClassInfo(TestNestedBean.class);
        System.out.println(classInfo);
    }

    @Test
    public void testDeepLevel() {
        ExcelClassInfo classInfo = ExcelClassInfoReader.readExcelClassInfo(TestNestedBean.class);
        System.out.println(classInfo.getDeepestLevel());
        System.out.println(classInfo);
    }

    @Test
    public void testFieldCount() {
        ExcelClassInfo classInfo = ExcelClassInfoReader.readExcelClassInfo(TestNestedBean.class);
        System.out.println(classInfo.getColumnInfo().get("嵌套bean").getCountOfBaseField());
    }

    @Test
    public void testRandomGen() {
        List<TestNestedBean> beans = genRandom(TestNestedBean.class);
        System.out.println(beans);
    }

    public static <T> List<T> genRandom(Class<T> clazz) {
        Field[] fs = clazz.getDeclaredFields();
        if(fs == null) {
            return null;
        }
        List<T> beans = new ArrayList<T>();
        for(int i = 0; i < 10; ++i) {
            try {
                beans.add(recursiveGen(clazz));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return beans;
    }

    public static <T> T recursiveGen(Class<T> clazz) throws Exception {
        Field[] fs = clazz.getDeclaredFields();
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        T instance = constructor.newInstance();
        if(fs == null) {
            return instance;
        }
        for(Field field : fs) {
            Class<?> valueClass;
            field.setAccessible(true);
            if(TypeUtils.isBaseType(valueClass = field.getType())) {
                Object randomVal = genRandomValue(valueClass);
                field.set(instance, randomVal);
            } else {
                Object valueInstance = recursiveGen(valueClass);
                field.set(instance, valueInstance);
            }
        }
        return instance;
    }

    private static Random random = new Random();

    private static Object genRandomValue(Class<?> valueClass) {
        StringBuilder builder = new StringBuilder();
        if(valueClass == String.class) {
            int randomInt = random.nextInt(3);
            switch(randomInt) {
                case 0:
                    return RandomValue.getChineseName();
                case 1:
                    return RandomValue.getRoad();
                case 2:
                    return RandomValue.getTel();
                default:
                    return RandomValue.getEmail(5, 10);
            }
        } else if(valueClass == boolean.class) {
            return random.nextBoolean();
        } else if(valueClass == Integer.class) {
            return random.nextInt();
        }
        return null;
    }
}
