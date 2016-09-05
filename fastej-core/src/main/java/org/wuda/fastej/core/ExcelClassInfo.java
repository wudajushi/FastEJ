package org.wuda.fastej.core;


import org.wuda.fastej.annotation.ExcelType;
import org.wuda.fastej.util.CollectionUtils;

import java.util.*;

/**
 * The type Excel class info.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:20
 */
public class ExcelClassInfo {
    /**
     * The Raw class.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:20
     */
    private final Class<?> rawClass;
    /**
     * The Field info.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:20
     */
    private final Map<String, ExcelBaseFieldInfo> columnInfo;

    /**
     * The Field info.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-03 15:32:46
     */
    private final Map<String, ExcelBaseFieldInfo> fieldInfo;
    /**
     * The Input type.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:20
     */
    private final ExcelType inputType;
    /**
     * The Output type.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:20
     */
    private final ExcelType outputType;

    /**
     * Instantiates a new Excel class info.
     *
     * @param rawClass   the raw class
     * @param columnInfo the column info
     * @param inputType  the input type
     * @param outputType the output type
     */
    public ExcelClassInfo(Class<?> rawClass, Map<String, ExcelBaseFieldInfo> columnInfo, Map<String,
            ExcelBaseFieldInfo> fieldInfo, ExcelType inputType, ExcelType outputType) {
        this.rawClass = rawClass;
        //排序
        this.fieldInfo = sortFields(fieldInfo);
        this.columnInfo = sortFields(columnInfo);
        this.inputType = inputType;
        this.outputType = outputType;
    }

    public Map<String, ExcelBaseFieldInfo> getFieldInfo() {
        return fieldInfo == null ? null : Collections.unmodifiableMap(fieldInfo);
    }

    /**
     * Gets raw class.
     *
     * @return the raw class
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:20
     */
    public Class<?> getRawClass() {
        return rawClass;
    }

    /**
     * Gets field info.
     *
     * @return the field info
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:20
     */
    public Map<String, ExcelBaseFieldInfo> getColumnInfo() {
        return columnInfo == null ? null : Collections.unmodifiableMap(columnInfo);
    }

    /**
     * Gets input type.
     *
     * @return the input type
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:20
     */
    public ExcelType getInputType() {
        return inputType;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        ExcelClassInfo that = (ExcelClassInfo) o;

        if(!rawClass.equals(that.rawClass)) return false;
        if(!columnInfo.equals(that.columnInfo)) return false;
        if(inputType != that.inputType) return false;
        return outputType == that.outputType;

    }

    @Override
    public int hashCode() {
        int result = rawClass.hashCode();
        result = 31 * result + columnInfo.hashCode();
        result = 31 * result + inputType.hashCode();
        result = 31 * result + outputType.hashCode();
        return result;
    }

    /**
     * Gets output type.
     *
     * @return the output type
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:20
     */
    public ExcelType getOutputType() {
        return outputType;
    }

    /**
     * 扫描出最深嵌套深度
     *
     * @return the deepest level
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-02 15:38:12
     */
    public int getDeepestLevel() {
        if(CollectionUtils.isEmpty(columnInfo)) {
            return 0;
        }
        return recursiveDeepestLevel(columnInfo, 0);
    }

    /**
     * 递归扫描出最深深度
     *
     * @param currentFieldInfos the current field infos
     * @param deepLevel         the deep level
     * @return the current field info deepest level
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-02 15:38:12
     */
    private int recursiveDeepestLevel(Map<String, ExcelBaseFieldInfo> currentFieldInfos, int deepLevel) {
        if(currentFieldInfos == null) {
            return deepLevel;
        }
        int result = deepLevel;
        int fieldLevel = deepLevel;
        for(Map.Entry<String, ExcelBaseFieldInfo> entry : currentFieldInfos.entrySet()) {
            ExcelBaseFieldInfo fieldInfo = entry.getValue();
            if(fieldInfo.isMixed()) {
                fieldLevel = recursiveDeepestLevel(fieldInfo.getChildFieldsInfo(), deepLevel + 1);
                if(fieldLevel > result) {
                    result = fieldLevel;
                }
            }
        }
        return result;
    }

    /**
     * 递归的把所有字段根据index排序，如果index一样根据自然顺序排序
     *
     * @param originMap the origin map
     * @return the map
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-08-03 15:32:05
     */
    public Map<String, ExcelBaseFieldInfo> sortFields(Map<String, ExcelBaseFieldInfo> originMap) {
        if(CollectionUtils.isEmpty(originMap)) {
            return originMap;
        }
        TreeMap<String, ExcelBaseFieldInfo> sortedMap = new TreeMap<String, ExcelBaseFieldInfo>(originMap);
        List<Map.Entry<String, ExcelBaseFieldInfo>> entryList = new ArrayList<Map.Entry<String, ExcelBaseFieldInfo>>
                (sortedMap.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<String, ExcelBaseFieldInfo>>() {
            public int compare(Map.Entry<String, ExcelBaseFieldInfo> o1, Map.Entry<String, ExcelBaseFieldInfo> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        for(Iterator<Map.Entry<String, ExcelBaseFieldInfo>> iterator = sortedMap.entrySet().iterator(); iterator
                .hasNext(); ) {
            Map.Entry<String, ExcelBaseFieldInfo> entry = iterator.next();
            String key = entry.getKey();
            ExcelBaseFieldInfo oldField = entry.getValue();
            Map<String, ExcelBaseFieldInfo> sortedColumnChildMap = sortFields(oldField.getChildFieldsInfo());
            Map<String, ExcelBaseFieldInfo> sortedFieldChildMap = sortFields(oldField.getFieldNameKeyFields());
            ExcelBaseFieldInfo newField = new ExcelBaseFieldInfo(oldField.getFieldClass(), oldField.getFieldName(),
                    oldField.getColumnName(), oldField.getIndex(), sortedColumnChildMap, sortedFieldChildMap,
                    oldField.isMixed());
            entry.setValue(newField);
        }
        return sortedMap;
    }

    @Override
    public String toString() {
        return "ExcelClassInfo{" +
                "columnInfo=" + columnInfo +
                ", rawClass=" + rawClass +
                ", fieldInfo=" + fieldInfo +
                ", inputType=" + inputType +
                ", outputType=" + outputType +
                '}';
    }
}
