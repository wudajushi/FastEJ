package org.wuda.fastej.core;

import org.wuda.fastej.util.CollectionUtils;

import java.util.Collections;
import java.util.Map;

/**
 * The type Excel base field info.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:09
 */
public class ExcelBaseFieldInfo implements Comparable<ExcelBaseFieldInfo> {
    /**
     * The Field name.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:10
     */
    private final String fieldName;
    /**
     * The Column name.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:10
     */
    private final String columnName;
    /**
     * The Index.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:10
     */
    private final int index;
    /**
     * The Field class.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:10
     */
    private final Class<?> fieldClass;

    /**
     * The Child fields info.
     * 这个key是columnName
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:10
     */
    private final Map<String, ExcelBaseFieldInfo> childFieldsInfo;

    /**
     * The Field name key fields.
     * 这个key是fieldName
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-03 15:14:00
     */
    private final Map<String, ExcelBaseFieldInfo> fieldNameKeyFields;
    /**
     * The Is mixed.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:10
     */
    private final boolean isMixed;

    /**
     * The Is getter.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 15:52:11
     */
    private final boolean isGetter;

    /**
     * The Date pattern.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:21:27
     */
    private final String datePattern;

    /**
     * Instantiates a new Excel base field info.
     *
     * @param fieldClass         the field class
     * @param fieldName          the field name
     * @param columnName         the column name
     * @param index              the index
     * @param childFieldsInfo    the child fields info
     * @param fieldNameKeyFields the field name key fields
     * @param isMixed            the is mixed
     * @param isGetter           the is getter
     * @param datePattern        the date pattern
     */
    public ExcelBaseFieldInfo(Class<?> fieldClass, String fieldName, String columnName, int index, Map<String,
            ExcelBaseFieldInfo> childFieldsInfo, Map<String, ExcelBaseFieldInfo> fieldNameKeyFields, boolean isMixed,
                              boolean isGetter, String datePattern) {
        this.fieldClass = fieldClass;
        this.fieldName = fieldName;
        this.columnName = columnName;
        this.index = index;
        this.childFieldsInfo = childFieldsInfo;
        this.fieldNameKeyFields = fieldNameKeyFields;
        this.isMixed = isMixed;
        this.isGetter = isGetter;
        this.datePattern = datePattern;
    }

    /**
     * Gets date pattern.
     *
     * @return the date pattern
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-07 10:21:41
     */
    public String getDatePattern() {
        return datePattern;
    }


    /**
     * Gets child fields info.
     *
     * @return the child fields info
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:09
     */
    public Map<String, ExcelBaseFieldInfo> getChildFieldsInfo() {
        return childFieldsInfo == null ? null : Collections.unmodifiableMap(childFieldsInfo);
    }

    /**
     * Gets field name key fields.
     *
     * @return the field name key fields
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-03 15:15:36
     */
    public Map<String, ExcelBaseFieldInfo> getFieldNameKeyFields() {
        return fieldNameKeyFields == null ? null : Collections.unmodifiableMap(fieldNameKeyFields);
    }

    /**
     * Gets field name.
     *
     * @return the field name
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:10
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Gets column name.
     *
     * @return the column name
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:10
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Gets index.
     *
     * @return the index
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:10
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gets field class.
     *
     * @return the field class
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:10
     */
    public Class<?> getFieldClass() {
        return fieldClass;
    }

    /**
     * Is mixed boolean.
     *
     * @return the boolean
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:10
     */
    public boolean isMixed() {
        return isMixed;
    }

    /**
     * Is getter boolean.
     *
     * @return the boolean
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-09-06 15:52:35
     */
    public boolean isGetter() {
        return isGetter;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        ExcelBaseFieldInfo fieldInfo = (ExcelBaseFieldInfo) o;

        if(index != fieldInfo.index) return false;
        if(isMixed != fieldInfo.isMixed) return false;
        if(isGetter != fieldInfo.isGetter) return false;
        if(!fieldName.equals(fieldInfo.fieldName)) return false;
        if(!columnName.equals(fieldInfo.columnName)) return false;
        if(!fieldClass.equals(fieldInfo.fieldClass)) return false;
        return !(datePattern != null ? !datePattern.equals(fieldInfo.datePattern) : fieldInfo.datePattern != null);

    }

    @Override
    public int hashCode() {
        int result = fieldName.hashCode();
        result = 31 * result + columnName.hashCode();
        result = 31 * result + index;
        result = 31 * result + fieldClass.hashCode();
        result = 31 * result + (isMixed ? 1 : 0);
        result = 31 * result + (isGetter ? 1 : 0);
        result = 31 * result + (datePattern != null ? datePattern.hashCode() : 0);
        return result;
    }

    public int compareTo(ExcelBaseFieldInfo o) {
        if(o == null || o == this) {
            return 0;
        }
        if(o.index == this.index) {
            return this.columnName.compareToIgnoreCase(o.columnName);
        }
        return this.index - o.index;
    }


    /**
     * 获取当前字段或bean平摊开所占的列数
     *
     * @return the int
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-03 14:52:09
     */
    public int getCountOfBaseField() {
        return recusiveCalcFieldCount(this) + 1;
    }

    /**
     * 递归计算当前bean平摊开后所占的列数
     *
     * @param fieldInfo the field info
     * @return the int
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-03 15:13:59
     */
    private int recusiveCalcFieldCount(ExcelBaseFieldInfo fieldInfo) {
        int allCount = 0;
        if(fieldInfo.isMixed()) {
            if(CollectionUtils.isEmpty(fieldInfo.getChildFieldsInfo())) {
                return allCount;
            }
            allCount += fieldInfo.getChildFieldsInfo().size() - 1;
            for(Map.Entry<String, ExcelBaseFieldInfo> entry : fieldInfo.getChildFieldsInfo().entrySet()) {
                allCount += recusiveCalcFieldCount(entry.getValue());
            }
            return allCount;
        } else {
            return allCount;
        }
    }

    @Override
    public String toString() {
        return "ExcelBaseFieldInfo{" +
                "childFieldsInfo=" + childFieldsInfo +
                ", fieldName='" + fieldName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", index=" + index +
                ", fieldClass=" + fieldClass +
                ", fieldNameKeyFields=" + fieldNameKeyFields +
                ", isMixed=" + isMixed +
                ", isGetter=" + isGetter +
                ", datePattern='" + datePattern + '\'' +
                '}';
    }
}
