package org.wuda.fastej.serializer;

import org.wuda.fastej.core.ExcelClassInfo;
import org.wuda.fastej.core.ExportConfiguration;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * Created by dell on 2016/8/2.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-08-04 15:22:51
 */
public interface BeanSerializer extends EJSerializer {
    /**
     * 通过beanList生成Workbook
     * @param <T>            the type parameter
     * @param beanList       the bean list
     * @param excelClassInfo the excel class info
     * @param configuration  the configuration
     * @return the workbook
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-04 15:22:51
     */
    <T> Workbook serializer(List<T> beanList, ExcelClassInfo excelClassInfo, ExportConfiguration configuration);
}
