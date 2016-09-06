package org.wuda.fastej.serializer.facade;

import org.apache.poi.ss.usermodel.Workbook;
import org.wuda.fastej.core.ExcelClassInfo;
import org.wuda.fastej.core.ExcelClassInfoReader;
import org.wuda.fastej.core.ExportConfiguration;
import org.wuda.fastej.serializer.BeanSerializer;
import org.wuda.fastej.serializer.JavaBeanSerializer;
import org.wuda.fastej.util.Assert;

import java.util.List;

/**
 * 导出门面类
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-09-06 11:39:30
 */
public class ExcelExpFacade {
    /**
     * The constant beanSerializer.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 11:39:30
     */
    private static BeanSerializer beanSerializer = new JavaBeanSerializer();

    /**
     * From java bean workbook.
     *
     * @param <T>           the type parameter
     * @param beanList      the bean list
     * @param beanClass     the bean class
     * @param configuration the configuration
     * @return the workbook
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 11:39:30
     */
    public static <T> Workbook fromJavaBean(List<T> beanList, Class<T> beanClass, ExportConfiguration configuration) {
        Assert.notEmpty(beanList, "beanList must not be empty !");
        Assert.notNull(beanClass, "beanClass must not be null !");
        Assert.notNull(configuration, "ExportConfiguration must not be null !");
        ExcelClassInfo classInfo = ExcelClassInfoReader.readExcelClassInfo(beanClass);
        return beanSerializer.serializer(beanList, classInfo, configuration);
    }

    /**
     * From java bean workbook.
     *
     * @param <T>       the type parameter
     * @param beanList  the bean list
     * @param beanClass the bean class
     * @return the workbook
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 11:39:30
     */
    public static <T> Workbook fromJavaBean(List<T> beanList, Class<T> beanClass) {
        return fromJavaBean(beanList, beanClass, new ExportConfiguration());
    }
}
