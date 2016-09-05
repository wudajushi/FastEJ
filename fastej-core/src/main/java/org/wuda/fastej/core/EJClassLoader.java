package org.wuda.fastej.core;

import org.wuda.fastej.util.Assert;

/**
 * The type Ej class loader.
 *
 * @author :<a href="mailto:450783043@qq.com">悟达</a>
 * @date :2016-07-22 17:42:02
 */
public class EJClassLoader extends ClassLoader {
    /**
     * Instantiates a new Ej class loader.
     */
    public EJClassLoader(Class<?> baseClass) {
        super(getParentClassLoader(baseClass));
    }

    /**
     * Instantiates a new Ej class loader.
     *
     * @param parent the parent
     */
    public EJClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * Define class public class.
     *
     * @param name the name
     * @param b    the b
     * @param off  the off
     * @param len  the len
     * @return the class
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:02
     */
    public Class<?> defineClassPublic(String name, byte[] b, int off, int len) {
        return defineClass(name, b, off, len);
    }

    /**
     * Gets parent class loader.
     *
     * @return the parent class loader
     * @author :<a href="mailto:450783043@qq.com">悟达</a>
     * @date :2016-07-22 17:42:02
     */
    static ClassLoader getParentClassLoader(Class<?> baseClass) {
        Assert.notNull(baseClass, "Base class must not be null !");
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if(contextClassLoader != null) {
            try {
                contextClassLoader.loadClass(baseClass.getName());
                return contextClassLoader;
            } catch(ClassNotFoundException e) {
                // skip
            }
        }
        return baseClass.getClassLoader();
    }
}
