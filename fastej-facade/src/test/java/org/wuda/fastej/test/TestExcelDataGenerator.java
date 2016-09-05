package org.wuda.fastej.test;

import junit.framework.TestCase;
import org.apache.poi.ss.usermodel.Workbook;
import org.wuda.fastej.Excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试excel的生成器
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-09-05 18:24:45
 */
public class TestExcelDataGenerator extends TestCase {

    /**
     * Test generate.
     *
     * @throws IllegalAccessException the illegal access exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-05 18:24:45
     */
    public void testGenerate() throws IllegalAccessException, IOException {
//        System.out.println(gen(Test20W.class, 10));
        Workbook workbook = Excel.fromJavaBean(gen(Test20W.class, 5000), Test20W.class);
        workbook.write(new FileOutputStream("D:\\5000.xlsx"));

        workbook = Excel.fromJavaBean(gen(Test20W.class, 10000), Test20W.class);
        workbook.write(new FileOutputStream("D:\\1W.xlsx"));

        workbook = Excel.fromJavaBean(gen(Test20W.class, 20000), Test20W.class);
        workbook.write(new FileOutputStream("D:\\2W.xlsx"));

        workbook = Excel.fromJavaBean(gen(Test20W.class, 50000), Test20W.class);
        workbook.write(new FileOutputStream("D:\\5W.xlsx"));

        workbook = Excel.fromJavaBean(gen(Test20W.class, 100000), Test20W.class);
        workbook.write(new FileOutputStream("D:\\10W.xlsx"));

        workbook = Excel.fromJavaBean(gen(Test20W.class, 200000), Test20W.class);
        workbook.write(new FileOutputStream("D:\\20W.xlsx"));
    }

    /**
     * Gen list.
     *
     * @param <T>       the type parameter
     * @param beanClass the bean class
     * @param size      the size
     * @return the list
     * @throws IllegalAccessException the illegal access exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-05 18:24:45
     */
    public static <T> List<T> gen(Class<T> beanClass, int size) throws IllegalAccessException {
        if(beanClass == null || size <= 0) {
            return null;
        }
        List<Field> fields = findAllDeclaredFields(beanClass);
        List<T> list = new ArrayList<T>();
        for(int i = 0; i < size; i++) {
            T bean = instantiateClass(beanClass);
            for(Field f : fields) {
                f.setAccessible(true);
                if(f.getType() == Integer.class || f.getType() == int.class) {
                    f.set(bean, RandomValue.getNum(0, 10000));
                } else if(f.getType() == String.class) {
                    f.set(bean, getRandomString());
                }
            }
            list.add(bean);
        }
        return list;
    }


    /**
     * Gets random string.
     *
     * @return the random string
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-05 18:24:45
     */
    public static String getRandomString() {
        int num = RandomValue.getNum(0, 3);
        switch(num) {
            case 0:
                return RandomValue.getRoad();
            case 1:
                return RandomValue.getChineseName();
            case 2:
                return RandomValue.getEmail(0, 10);
            case 3:
                return RandomValue.getTel();
        }
        return "";
    }

    /**
     * Instantiate class t.
     *
     * @param <T>       the type parameter
     * @param beanClass the bean class
     * @return the t
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-05 18:24:45
     */
    public static <T> T instantiateClass(Class<T> beanClass) {
        if(beanClass == null) {
            return null;
        }
        try {
            Constructor<T> constructor = beanClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Find all declared fields list.
     *
     * @param beanClass the bean class
     * @return the list
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-05 18:24:45
     */
    public static List<Field> findAllDeclaredFields(Class<?> beanClass) {
        List<Field> fields = new ArrayList<Field>();
        Field[] fs = beanClass.getDeclaredFields();
        for(Field f : fs) {
            fields.add(f);
        }
        if(beanClass.getSuperclass() == Object.class) {
            return fields;
        }
        List<Field> recursiveList = findAllDeclaredFields(beanClass.getSuperclass());
        fields.addAll(recursiveList);
        return fields;
    }

}
