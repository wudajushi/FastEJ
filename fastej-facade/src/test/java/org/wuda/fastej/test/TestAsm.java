package org.wuda.fastej.test;

import org.wuda.fastej.core.ExcelBeanBinderData;
import org.wuda.fastej.deserializer.ASMGeneratorDeserializer;
import junit.framework.TestCase;
import org.objectweb.asm.*;
import org.objectweb.asm.util.CheckClassAdapter;

import java.io.IOException;

import static org.wuda.fastej.util.ASMUtils.desc;
import static org.wuda.fastej.util.ASMUtils.type;

/**
 * The type Test asm.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-27 09:27:51
 */
public class TestAsm extends TestCase implements Opcodes {

    /**
     * Deserliaze t.
     *
     * @param <T> the type parameter
     * @return the t
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:51
     */
    public <T> T deserliaze() {
        ClassWriter cw = new ClassWriter(0);
        CheckClassAdapter cca = new CheckClassAdapter(cw);
        cca.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "ASM$FASTEJ_" + 0 + "GEN" + "$TestBean", null, null, new
                String[]{type(ASMGeneratorDeserializer.class)});
        MethodVisitor mv1 = cca.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "deserialize", "()Ljava/util/List;",
                "<T:Ljava/lang/Object;>()Ljava/util/List<TT;>;", null);
        mv1.visitCode();
        mv1.visitLocalVariable("excelBeanBinderData", desc(ExcelBeanBinderData.class), null, null, null, 1);
        return null;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-27 09:27:51
     */
    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader(type(TestAsm.class));
        ClassWriter cw = new ClassWriter(0);
    }

    public void test1() {
        ClassWriter cw = new ClassWriter(0);
        CheckClassAdapter cwp = new CheckClassAdapter(cw);
        cwp.visit(V1_7, ACC_PUBLIC + ACC_SUPER, "TestGener", null, "java/lang/Object", null);
        MethodVisitor mv = cwp.visitMethod(ACC_PUBLIC, "test", "()V", null, null);
        Label l1 = new Label();
        Label l2 = new Label();
        mv.visitCode();
        mv.visitLabel(l1);
        mv.visitInsn(ICONST_5);
        mv.visitVarInsn(ISTORE, 1);
        mv.visitVarInsn(ILOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "org/wuda/fastej/test/TestAsm", "setId", "(I)V");
        mv.visitLabel(l2);
        mv.visitLocalVariable("id", "I", null, l1, l2, 1);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 2);
        mv.visitEnd();
        cwp.visitEnd();
    }

    public void test2() {
        ClassWriter cw = new ClassWriter(0);
        CheckClassAdapter cwp = new CheckClassAdapter(cw);
        cwp.visit(V1_7, ACC_PUBLIC + ACC_SUPER, "TestGener", null, "java/lang/Object", null);
        MethodVisitor mv = cwp.visitMethod(ACC_PUBLIC, "testA", "()V", null, null);
        mv.visitCode();
        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitLineNumber(93, l0);
        mv.visitInsn(ICONST_0);
        mv.visitVarInsn(ISTORE, 1);
        Label l1 = new Label();
        mv.visitLabel(l1);
        mv.visitLineNumber(94, l1);
        mv.visitVarInsn(ILOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, "org/wuda/fastej/test/TestAsm", "setId", "(I)V");
        Label l2 = new Label();
        mv.visitLabel(l2);
        mv.visitLineNumber(95, l2);
        mv.visitInsn(RETURN);
        Label l3 = new Label();
        mv.visitLabel(l3);
        mv.visitLocalVariable("this", "Lorg/wuda/fastej/test/TestAsm;", null, l0, l3, 0);
        mv.visitLocalVariable("i", "I", null, l1, l3, 1);
        mv.visitMaxs(1, 2);
        mv.visitEnd();
        cwp.visitEnd();
    }

    public static void setId(int id) {

    }

    public void testAsm() {
        Integer i = null;
        while(i == 1) {
            if(i != null) {
                System.out.println(111);
                continue;
            }
        }
    }

    public void testA() {
        int i = 0;
        setId(i);
    }

}
