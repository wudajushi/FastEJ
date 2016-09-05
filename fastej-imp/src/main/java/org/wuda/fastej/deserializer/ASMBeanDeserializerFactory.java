package org.wuda.fastej.deserializer;

import org.wuda.fastej.core.*;
import org.wuda.fastej.util.ASMUtils;
import org.wuda.fastej.util.TypeUtils;
import de.jkeylockmanager.manager.KeyLockManager;
import de.jkeylockmanager.manager.KeyLockManagers;
import de.jkeylockmanager.manager.LockCallback;
import de.jkeylockmanager.manager.ReturnValueLockCallback;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.CheckClassAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The type Asm bean deserializer factory.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:39
 */
public class ASMBeanDeserializerFactory implements BeanDeserializerFactory, Opcodes {
    private static final Logger logger = LoggerFactory.getLogger(ASMBeanDeserializerFactory.class);
    /**
     * The constant packageName.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:39
     */
    private static final String packageName = ASMBeanDeserializerFactory.class.getPackage().getName();
    /**
     * The constant genName.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:39
     */
    private static final String genName = packageName + ".ASM$FASTEJ$GEN_%s$%s";
    /**
     * The constant seed.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:39
     */
    private static final AtomicLong seed = new AtomicLong(0);
    /**
     * The constant classLoader.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:39
     */
    private static final EJClassLoader classLoader = new EJClassLoader(JavaBeanDeserializer.class);
    /**
     * The constant clazzCache.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:39
     */
    private static final ConcurrentMap<ExcelClassInfo, Class<?>> clazzCache = new ConcurrentHashMap<ExcelClassInfo,
            Class<?>>();

    /**
     * The constant deserializerCache.
     *
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:39
     */
    private static final ConcurrentMap<ExcelClassInfo, ASMGeneratorDeserializer> deserializerCache = new
            ConcurrentHashMap<ExcelClassInfo, ASMGeneratorDeserializer>();
    private static final KeyLockManager keyLockManager = KeyLockManagers.newLock();

    public ASMGeneratorDeserializer gen(final ExcelClassInfo excelClassInfo) {
        Class<?> clazz = keyLockManager.executeLocked(excelClassInfo, new ReturnValueLockCallback<Class<?>>() {
            public Class<?> doInLock() {
                return clazzCache.get(excelClassInfo);
            }
        });
        ASMGeneratorDeserializer deserializer = keyLockManager.executeLocked(excelClassInfo, new
                ReturnValueLockCallback<ASMGeneratorDeserializer>() {
            public ASMGeneratorDeserializer doInLock() {
                return deserializerCache.get(excelClassInfo);
            }
        });
        if(clazz == null) {
            logger.info("Generate ASM class start !");
            ExcelBeanBinderData binderData = JavaBeanDeserializer.CURRENT_EXCELBEAN.get();
            String beanClass = ASMUtils.type(binderData.getExcelClassInfo().getRawClass());
            String beanClassSimpleName = binderData.getExcelClassInfo().getRawClass().getSimpleName();
            String beanClassDesc = ASMUtils.desc(binderData.getExcelClassInfo().getRawClass());
            List<String> makeSureOrdered = new ArrayList<String>();
            Map<String, ExcelCellData> firstCellData = binderData.getAllCellDatas().get(0);
            for(String key : firstCellData.keySet()) {
                makeSureOrdered.add(key);
            }
            String classFullName = String.format(genName, seed.getAndIncrement(), beanClassSimpleName);
            ClassWriter cwp = new ClassWriter(0);
            CheckClassAdapter cw = new CheckClassAdapter(cwp);
            cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, classFullName.replace(".", "/"), null,
                    "java/lang/Object", new String[]{ASMUtils.type(ASMGeneratorDeserializer.class)});
            MethodVisitor mv;
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
            mv = cw.visitMethod(ACC_PUBLIC + ACC_FINAL, "deserialize", "()Ljava/util/List;",
                    "<T:Ljava/lang/Object;>" +
                    "()" + "Ljava/util/List<TT;>;", null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(47, l0);
            mv.visitFieldInsn(GETSTATIC, "org/wuda/fastej/deserializer/JavaBeanDeserializer",
                    "CURRENT_EXCELBEAN", "Ljava/lang/ThreadLocal;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ThreadLocal", "get", "()Ljava/lang/Object;");
            mv.visitTypeInsn(CHECKCAST, "org/wuda/fastej/core/ExcelBeanBinderData");
            mv.visitVarInsn(ASTORE, 1);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(48, l1);
            mv.visitVarInsn(ALOAD, 1);
            Label l2 = new Label();
            mv.visitJumpInsn(IFNONNULL, l2);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(49, l3);
            mv.visitTypeInsn(NEW, "org/wuda/fastej/core/FastEJException");
            mv.visitInsn(DUP);
            mv.visitLdcInsn("Unexpected NullPointer ! The ThreadLocal JavaBeanDeserializer.CURRENT_EXCELBEAN value " +
                    "is" + " null !");
            mv.visitMethodInsn(INVOKESPECIAL, "org/wuda/fastej/core/FastEJException", "<init>", "" + "" +
                    "(Ljava/lang/String;)V");
            mv.visitInsn(ATHROW);
            mv.visitLabel(l2);
            mv.visitLineNumber(51, l2);
            mv.visitTypeInsn(NEW, "java/util/ArrayList");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V");
            mv.visitVarInsn(ASTORE, 2);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(52, l4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/wuda/fastej/core/ExcelBeanBinderData",
                    "getAllCellDatas", "()" + "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(53, l5);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKESTATIC, "org/wuda/fastej/util/CollectionUtils", "isEmpty", "" + "" +
                    "(Ljava/util/Collection;)Z");
            Label l6 = new Label();
            mv.visitJumpInsn(IFEQ, l6);
            Label l7 = new Label();
            mv.visitLabel(l7);
            mv.visitLineNumber(54, l7);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l6);
            mv.visitLineNumber(56, l6);
            mv.visitFieldInsn(GETSTATIC, "org/wuda/fastej/core/DefaultTypeConverter", "INSTANCE",
                    "Lorg/wuda/fastej/core/DefaultTypeConverter;");
            mv.visitVarInsn(ASTORE, 4);

            Label l9 = new Label();
            mv.visitLabel(l9);
            mv.visitLineNumber(58, l9);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;");
            mv.visitVarInsn(ASTORE, 6);
            Label l10 = new Label();
            mv.visitLabel(l10);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z");
            Label l11 = new Label();
            mv.visitJumpInsn(IFEQ, l11);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;");
            mv.visitTypeInsn(CHECKCAST, "java/util/Map");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitJumpInsn(IFNULL, l10);
            Label l8 = new Label();
            mv.visitLabel(l8);
            mv.visitLineNumber(57, l8);
            mv.visitTypeInsn(NEW, beanClass);
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, beanClass, "<init>", "()V");
            mv.visitVarInsn(ASTORE, 5);
            Label l12 = new Label();
            mv.visitLabel(l12);
            mv.visitLineNumber(59, l12);
            for(int i = 0; i < makeSureOrdered.size(); ++i) {
                String key = makeSureOrdered.get(i);
                int seed = i + 9;
                Class<?> fieldClass = firstCellData.get(key).getFieldInfo().getFieldClass();
                mv.visitVarInsn(ALOAD, 7);
                mv.visitLdcInsn(key);
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "get", "(Ljava/lang/Object;)" +
                        "Ljava/lang/Object;");
                mv.visitTypeInsn(CHECKCAST, "org/wuda/fastej/core/ExcelCellData");
                mv.visitVarInsn(ASTORE, 8);
                mv.visitVarInsn(ALOAD, 8);
                Label lcontinue = new Label();
                mv.visitJumpInsn(IFNULL, lcontinue);
                mv.visitVarInsn(ALOAD, 4);
                mv.visitVarInsn(ALOAD, 8);
                mv.visitMethodInsn(INVOKEVIRTUAL, "org/wuda/fastej/core/ExcelCellData", "getCellData", "()"
                        + "Ljava/lang/String;");
                if(TypeUtils.isPrimitive(fieldClass)) {
                    visitPrimitiveConvert(fieldClass, seed, mv);
                } else {
                    mv.visitVarInsn(ALOAD, 8);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "org/wuda/fastej/core/ExcelCellData", "getFieldInfo",
                            "()" + "Lorg/wuda/fastej/core/ExcelBaseFieldInfo;");
                    mv.visitMethodInsn(INVOKEVIRTUAL, "org/wuda/fastej/core/ExcelBaseFieldInfo",
                            "getFieldClass", "" +
                            "()" + "Ljava/lang/Class;");
                    mv.visitMethodInsn(INVOKEINTERFACE, "org/wuda/fastej/core/TypeConverter", "convert", ""
                            + "" +
                            "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;");
                    mv.visitTypeInsn(CHECKCAST, ASMUtils.type(fieldClass));
                }
                visitStoreVariable(fieldClass, seed, mv);
                mv.visitVarInsn(ALOAD, 5);
                visitLoadVariable(fieldClass, seed, mv);
                String keySetter = Character.toUpperCase(key.charAt(0)) + key.substring(1);
                mv.visitMethodInsn(INVOKEVIRTUAL, beanClass, "set" + keySetter, "(" + ASMUtils.desc
                        (fieldClass) + ")V");
                mv.visitLabel(lcontinue);
            }
            Label l18 = new Label();
            mv.visitLabel(l18);
            mv.visitLineNumber(65, l18);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z");
            mv.visitInsn(POP);
            Label l19 = new Label();
            mv.visitLabel(l19);
            mv.visitLineNumber(66, l19);
            mv.visitJumpInsn(GOTO, l10);
            mv.visitLabel(l11);
            mv.visitLineNumber(67, l11);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ARETURN);
            Label l20 = new Label();
            mv.visitLabel(l20);

            for(int i = 0; i < makeSureOrdered.size(); ++i) {
                int seed = i + 9;
                String key = makeSureOrdered.get(i);
                ExcelCellData cellDataItem = firstCellData.get(key);
                mv.visitLocalVariable(cellDataItem.getFieldName(), ASMUtils.desc(cellDataItem.getFieldInfo()
                        .getFieldClass()), null, l12, l18, seed);
            }
            mv.visitLocalVariable("cellDataItem", "Lorg/wuda/fastej/core/ExcelCellData;", null, l12, l19, 8);
            mv.visitLocalVariable("rowItem", "Ljava/util/Map;", "Ljava/util/Map<Ljava/lang/String;" +
                    "Lorg/wuda/fastej/core/ExcelCellData;>;", l12, l19, 7);
            mv.visitLocalVariable("i$", "Ljava/util/Iterator;", null, l10, l11, 6);
            mv.visitLocalVariable("this", "Lbean/TestAsm;", null, l0, l20, 0);
            mv.visitLocalVariable("excelBeanBinderData", "Lorg/wuda/fastej/core/ExcelBeanBinderData;", null, l1,
                    l20, 1);
            mv.visitLocalVariable("resultList", "Ljava/util/List;", "Ljava/util/List<" + beanClassDesc + ">;", l4,
                    l20, 2);
            mv.visitLocalVariable("allCellDatas", "Ljava/util/List;",
                    "Ljava/util/List<Ljava/util/Map<Ljava/lang/String;Lorg/wuda/fastej/core/ExcelCellData;>;>;",
                    l5, l20, 3);
            mv.visitLocalVariable("typeConverter", "Lorg/wuda/fastej/core/TypeConverter;", null, l8, l20, 4);
            mv.visitLocalVariable("bean", beanClassDesc, null, l9, l20, 5);
            mv.visitMaxs(3, 9 + makeSureOrdered.size());
            mv.visitEnd();
            cw.visitEnd();
            byte[] b = cwp.toByteArray();
            clazz = classLoader.defineClassPublic(classFullName, b, 0, b.length);
            final Class<?> fiClass = clazz;
            keyLockManager.executeLocked(excelClassInfo, new LockCallback() {
                public void doInLock() {
                    clazzCache.put(excelClassInfo, fiClass);
                }
            });

            try {
                deserializer = (ASMGeneratorDeserializer) clazz.newInstance();
                final ASMGeneratorDeserializer fiDeserializer = deserializer;
                keyLockManager.executeLocked(excelClassInfo, new LockCallback() {
                    public void doInLock() {
                        deserializerCache.put(excelClassInfo, fiDeserializer);
                    }
                });

            } catch(Exception e) {
                logger.error("Generate class instantiation error !", e);
                throw new EJBeanInstantiationException("Generate class instantiation error !", e);
            }
        }
        return deserializer;

    }

    private void visitLoadVariable(Class<?> fieldClass, int seed, MethodVisitor mv) {
        if(fieldClass == boolean.class || fieldClass == byte.class || fieldClass == short.class || fieldClass == int
                .class || fieldClass == char.class) {
            mv.visitVarInsn(ILOAD, seed);
        } else if(fieldClass == long.class) {
            mv.visitVarInsn(LLOAD, seed);
        } else if(fieldClass == double.class) {
            mv.visitVarInsn(DLOAD, seed);
        } else if(fieldClass == float.class) {
            mv.visitVarInsn(FLOAD, seed);
        } else {
            mv.visitVarInsn(ALOAD, seed);
        }
    }

    private void visitStoreVariable(Class<?> fieldClass, int seed, MethodVisitor mv) {
        if(fieldClass == boolean.class || fieldClass == byte.class || fieldClass == short.class || fieldClass == int
                .class || fieldClass == char.class) {
            mv.visitVarInsn(ISTORE, seed);
        } else if(fieldClass == long.class) {
            mv.visitVarInsn(LSTORE, seed);
        } else if(fieldClass == double.class) {
            mv.visitVarInsn(DSTORE, seed);
        } else if(fieldClass == float.class) {
            mv.visitVarInsn(FSTORE, seed);
        } else {
            mv.visitVarInsn(ASTORE, seed);
        }
    }

    private void visitPrimitiveConvert(Class<?> fieldClass, int seed, MethodVisitor mv) {
        if(fieldClass == boolean.class) {
            mv.visitMethodInsn(INVOKEINTERFACE, "org/wuda/fastej/core/TypeConverter", "convertToBoolean",
                    "" + "" +
                    "(Ljava/lang/Object;)Z");
        } else if(fieldClass == byte.class) {
            mv.visitMethodInsn(INVOKEINTERFACE, "org/wuda/fastej/core/TypeConverter", "convertToByte", "" +
                    "" +
                    "(Ljava/lang/Object;)B");
        } else if(fieldClass == short.class) {
            mv.visitMethodInsn(INVOKEINTERFACE, "org/wuda/fastej/core/TypeConverter", "convertToShort", ""
                    + "" +
                    "(Ljava/lang/Object;)S");
        } else if(fieldClass == int.class) {
            mv.visitMethodInsn(INVOKEINTERFACE, "org/wuda/fastej/core/TypeConverter", "convertToInt", "" +
                    "" +
                    "(Ljava/lang/Object;)I");
        } else if(fieldClass == char.class) {
            mv.visitMethodInsn(INVOKEINTERFACE, "org/wuda/fastej/core/TypeConverter", "convertToChar", "" +
                    "" +
                    "(Ljava/lang/Object;)C");
        } else if(fieldClass == long.class) {
            mv.visitMethodInsn(INVOKEINTERFACE, "org/wuda/fastej/core/TypeConverter", "convertToLong", "" +
                    "" +
                    "(Ljava/lang/Object;)J");
        } else if(fieldClass == double.class) {
            mv.visitMethodInsn(INVOKEINTERFACE, "org/wuda/fastej/core/TypeConverter", "convertToDouble", ""
                    + "" +
                    "(Ljava/lang/Object;)D");
        } else if(fieldClass == float.class) {
            mv.visitMethodInsn(INVOKEINTERFACE, "org/wuda/fastej/core/TypeConverter", "convertToFloat", ""
                    + "" +
                    "(Ljava/lang/Object;)F");
        }
    }
}
