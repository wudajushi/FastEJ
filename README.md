# FastEJ说明

## 简介
在互联网信息发展的时代，对报表数据的处理需要一个通用化的解决方案。而导入Excel文件到内存、导出内存数据到Excel文件
是一个普遍化的需求。本项目旨在设计一个Object-Excel映射的通用解决方案。

## 使用说明
### 核心类
* `Excel`
此类为FastEJ导入导出门面类  提供了三种方法
1. `toJavaBean`  - Excel反序列化到JavaBean（导入）
2. `toJavaBeanWithValidate` - Excel反序列化到JavaBean带验证（导入）
3. `fromJavaBean` - JavaBean序列化到Excel文件（导出）

### 核心注解
* `ExcelBean`
  注解在需要导入导出的bean上（必须），参数有导入类型和导出类型两种（XLSX,XLS）
* `ExcelField`
  注解在bean的字段上（非getter），参数有index（导出使用，顺序），colunmName（对应excel表头值）
* `ExcelNestedBean`
  注解在嵌套bean上，用于支持复合表头
* `EJValidationMessageKey`
  注解在bean上，用于带验证的导入后返回的Map所使用的key

### 导入验证功能
本库集成hibernate-validator，支持标准的validator注解。
同时使用自定义的占位符来实现默认几行几列的提示

## 部分实现
* 导入功能使用的ASM字节码框架，比反射性能要好
* 导入导出功能使用的Excel库是POI

## 关于
项目创建人： [悟达](mailto:450783043@qq.com)
作者：[悟达](mailto:450783043@qq.com)