package org.wuda.fastej;

import org.wuda.fastej.core.*;
import org.wuda.fastej.deserializer.BeanDeserializer;
import org.wuda.fastej.deserializer.JavaBeanDeserializer;
import org.wuda.fastej.sax.ExcelRawDataReader;
import org.wuda.fastej.serializer.BeanSerializer;
import org.wuda.fastej.serializer.JavaBeanSerializer;
import org.wuda.fastej.util.Assert;
import org.wuda.fastej.validate.ValidatedBean;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * The type Excel.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:43:23
 */
public class Excel {
    /**
     * The constant beanDeserializer.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:43:23
     */
    private static BeanDeserializer beanDeserializer = new JavaBeanDeserializer();
    /**
     * The constant beanSerializer.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-04 17:29:47
     */
    private static BeanSerializer beanSerializer = new JavaBeanSerializer();

    /**
     * To java bean list.
     *
     * @param <T>       the type parameter
     * @param file      the file
     * @param beanClass the bean class
     * @return the list
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:43:23
     */
    public static <T> List<T> toJavaBean(File file, Class<T> beanClass) throws IOException {
        Assert.notNull(file, "File must not be null !");
        Assert.notNull(beanClass, "Bean class must not be null !");
        return toJavaBean(new FileInputStream(file), beanClass);
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
     * @date :2016-07-28 17:35:24
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
     * @date :2016-07-28 17:35:24
     */
    public static <T> Map<Object, ValidatedBean<T>> toJavaBeanWithValidate(String filePath, Class<T> beanClass)
            throws IOException {
        Assert.notNull(filePath, "File must not be null !");
        Assert.notNull(beanClass, "Bean class must not be null !");
        return toJavaBeanWithValidate(new FileInputStream(filePath), beanClass);
    }

    /**
     * To java bean with validate map.
     *
     * @param <T>       the type parameter
     * @param is        the is
     * @param beanClass the bean class
     * @return the map
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-28 17:35:24
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
     * @param filePath  the file path
     * @param beanClass the bean class
     * @return the list
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:43:23
     */
    public static <T> List<T> toJavaBean(String filePath, Class<T> beanClass) throws IOException {
        Assert.notNull(filePath, "filePath must not be null !");
        Assert.notNull(beanClass, "Bean class must not be null !");
        return toJavaBean(new FileInputStream(filePath), beanClass);
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
     * @date :2016-07-22 17:43:23
     */
    public static <T> List<T> toJavaBean(InputStream is, Class<T> beanClass) throws IOException {
        Assert.notNull(is, "InputStream must not be null !");
        Assert.notNull(beanClass, "Bean class must not be null !");
        ExcelClassInfo classInfo = ExcelClassInfoReader.readExcelClassInfo(beanClass);
        ExcelRawData rawData = ExcelRawDataReader.read(is, classInfo.getInputType());
        return beanDeserializer.deserialize(rawData, classInfo);
    }

    /**
     * 导出功能
     *
     * @param <T>           the type parameter
     * @param beanList      the bean list
     * @param beanClass     the bean class
     * @param configuration the configuration
     * @return the workbook
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-04 17:29:47
     */
    public static <T> Workbook fromJavaBean(List<T> beanList, Class<T> beanClass, ExportConfiguration configuration) {
        Assert.notEmpty(beanList, "beanList must not be empty !");
        Assert.notNull(beanClass, "beanClass must not be null !");
        Assert.notNull(configuration, "ExportConfiguration must not be null !");
        ExcelClassInfo classInfo = ExcelClassInfoReader.readExcelClassInfo(beanClass);
        return beanSerializer.serializer(beanList, classInfo, configuration);
    }

    /**
     * 导出功能
     *
     * @param <T>       the type parameter
     * @param beanList  the bean list
     * @param beanClass the bean class
     * @return the workbook
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-04 17:29:47
     */
    public static <T> Workbook fromJavaBean(List<T> beanList, Class<T> beanClass) {
        return fromJavaBean(beanList, beanClass, new ExportConfiguration());
    }
}
