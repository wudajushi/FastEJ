package org.wuda.fastej.deserializer.facade;

import org.wuda.fastej.core.ExcelClassInfo;
import org.wuda.fastej.core.ExcelClassInfoReader;
import org.wuda.fastej.core.ExcelRawData;
import org.wuda.fastej.deserializer.BeanDeserializer;
import org.wuda.fastej.deserializer.JavaBeanDeserializer;
import org.wuda.fastej.sax.ExcelRawDataReader;
import org.wuda.fastej.util.Assert;
import org.wuda.fastej.validate.ValidatedBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * The type Excel imp facade.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-09-06 11:40:44
 */
public class ExcelImpFacade {
    /**
     * The constant beanDeserializer.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 11:40:44
     */
    private static BeanDeserializer beanDeserializer = new JavaBeanDeserializer();

    /**
     * To java bean with validate map.
     *
     * @param <T>       the type parameter
     * @param is        the is
     * @param beanClass the bean class
     * @return the map
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 11:40:44
     */
    public static <T> Map<Object, ValidatedBean<T>> toJavaBeanWithValidate(InputStream is, Class<T> beanClass) throws
            IOException {

        Assert.notNull(is, "InputStream must not be null !");
        Assert.notNull(beanClass, "Bean class must not be null !");
        ExcelClassInfo classInfo = ExcelClassInfoReader.readExcelClassInfo(beanClass);
        ExcelRawData rawData = ExcelRawDataReader.read(is, classInfo.getInputType());
        return beanDeserializer.deserializeWithValidate(rawData, classInfo);
    }

    /**
     * To java bean list.
     *
     * @param <T>       the type parameter
     * @param is        the is
     * @param beanClass the bean class
     * @return the list
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 11:40:44
     */
    public static <T> List<T> toJavaBean(InputStream is, Class<T> beanClass) throws IOException {
        Assert.notNull(is, "InputStream must not be null !");
        Assert.notNull(beanClass, "Bean class must not be null !");
        ExcelClassInfo classInfo = ExcelClassInfoReader.readExcelClassInfo(beanClass);
        ExcelRawData rawData = ExcelRawDataReader.read(is, classInfo.getInputType());
        return beanDeserializer.deserialize(rawData, classInfo);
    }

    /**
     * To java bean with validate map.
     *
     * @param <T>       the type parameter
     * @param file      the file
     * @param beanClass the bean class
     * @return the map
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 11:40:44
     */
    public static <T> Map<Object, ValidatedBean<T>> toJavaBeanWithValidate(File file, Class<T> beanClass) throws
            IOException {
        Assert.notNull(file, "File must not be null !");
        Assert.notNull(beanClass, "Bean class must not be null !");
        return toJavaBeanWithValidate(new FileInputStream(file), beanClass);
    }

    /**
     * To java bean with validate map.
     *
     * @param <T>       the type parameter
     * @param filePath  the file path
     * @param beanClass the bean class
     * @return the map
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 11:40:44
     */
    public static <T> Map<Object, ValidatedBean<T>> toJavaBeanWithValidate(String filePath, Class<T> beanClass)
            throws IOException {
        Assert.notNull(filePath, "File must not be null !");
        Assert.notNull(beanClass, "Bean class must not be null !");
        return toJavaBeanWithValidate(new FileInputStream(filePath), beanClass);
    }

    /**
     * To java bean list.
     *
     * @param <T>       the type parameter
     * @param file      the file
     * @param beanClass the bean class
     * @return the list
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 11:40:44
     */
    public static <T> List<T> toJavaBean(File file, Class<T> beanClass) throws IOException {
        Assert.notNull(file, "File must not be null !");
        Assert.notNull(beanClass, "Bean class must not be null !");
        return toJavaBean(new FileInputStream(file), beanClass);
    }

    /**
     * To java bean list.
     *
     * @param <T>       the type parameter
     * @param filePath  the file path
     * @param beanClass the bean class
     * @return the list
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 11:40:44
     */
    public static <T> List<T> toJavaBean(String filePath, Class<T> beanClass) throws IOException {
        Assert.notNull(filePath, "filePath must not be null !");
        Assert.notNull(beanClass, "Bean class must not be null !");
        return toJavaBean(new FileInputStream(filePath), beanClass);
    }
}
