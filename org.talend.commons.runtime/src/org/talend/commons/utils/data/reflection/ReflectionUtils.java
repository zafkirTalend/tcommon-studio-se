// ============================================================================
//
// Copyright (C) 2006-2012 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.commons.utils.data.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * DOC ycbai class global comment. Detailled comment
 * 
 * General reflection utils.
 * 
 */
public class ReflectionUtils {

    private static Logger log = Logger.getLogger(ReflectionUtils.class.getName());

    /**
     * DOC ycbai Comment method "getPublicField".
     * 
     * Returns the value of a public field.
     * 
     * @param owner
     * @param fieldName
     * @return
     * @throws Exception
     */
    public static Object getPublicField(Object owner, String fieldName) {
        Object fieldValue = null;
        Class ownerClass = owner.getClass();
        try {
            Field field = ownerClass.getField(fieldName);
            fieldValue = field.get(owner);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }

        return fieldValue;
    }

    /**
     * DOC ycbai Comment method "getPrivateField".
     * 
     * Returns the value of a private field.
     * 
     * @param owner
     * @param fieldName
     * @return
     */
    public static Object getPrivateField(Object owner, String fieldName) {
        Object fieldValue = null;
        Class ownerClass = owner.getClass();
        try {
            Field f = ownerClass.getDeclaredField(fieldName);
            f.setAccessible(true);
            fieldValue = f.get(owner);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }

        return fieldValue;
    }

    /**
     * DOC ycbai Comment method "getStaticField".
     * 
     * Returns the value of a static field.
     * 
     * @param className
     * @param loader
     * @param fieldName
     * @return
     */
    public static Object getStaticField(String className, ClassLoader loader, String fieldName) {
        Object fieldValue = null;
        try {
            Class ownerClass = null;
            if (loader != null) {
                ownerClass = Class.forName(className, true, loader);
            } else {
                ownerClass = Class.forName(className);
            }
            Field field = ownerClass.getField(fieldName);
            fieldValue = field.get(ownerClass);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }

        return fieldValue;
    }

    /**
     * To set the field using the given <code>value</code>.
     * Added by Marvin Wang on Oct 19, 2012.
     */
    public static void setStaticFieldValue(String className, ClassLoader loader, String fieldName, Object value){
        try {
            Class ownerClass = null;
            if (loader != null) {
                ownerClass = Class.forName(className, true, loader);
            } else {
                ownerClass = Class.forName(className);
            }
            Field field = ownerClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(null, value);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }
    }
    
    public static Object getStaticField(String className, String fieldName) {
        return getStaticField(className, null, fieldName);
    }

    /**
     * DOC ycbai Comment method "invokeMethod".
     * 
     * Returns the value of a method.
     * 
     * @param owner
     * @param methodName
     * @param args
     * @return
     */
    public static Object invokeMethod(Object owner, String methodName, Object[] args) {
        Object returnValue = null;
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        try {
            Method method = ownerClass.getMethod(methodName, argsClass);
            returnValue = method.invoke(owner, args);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }

        return returnValue;
    }

    /**
     * DOC ycbai Comment method "invokeStaticMethod".
     * 
     * Returns the value of a static method.
     * 
     * @param className
     * @param loader
     * @param methodName
     * @param args
     * @return
     */
    public static Object invokeStaticMethod(String className, ClassLoader loader, String methodName, Object[] args) {
        Object returnValue = null;
        try {
            Class ownerClass = null;
            if (loader != null) {
                ownerClass = Class.forName(className, true, loader);
            } else {
                ownerClass = Class.forName(className);
            }
            Class[] argsClass = new Class[args.length];
            for (int i = 0, j = args.length; i < j; i++) {
                argsClass[i] = args[i].getClass();
            }
            Method method = ownerClass.getMethod(methodName, argsClass);
            returnValue = method.invoke(null, args);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }

        return returnValue;
    }

    public static Object invokeStaticMethod(String className, String methodName, Object[] args) {
        return invokeStaticMethod(className, null, methodName, args);
    }

    /**
     * DOC ycbai Comment method "newInstance".
     * 
     * Create a new instance of a class.
     * 
     * @param className
     * @param initialize
     * @param loader
     * @param args
     * @return
     */
    public static Object newInstance(String className, ClassLoader loader, Object[] args) {
        Object instance = null;
        try {
            Class newClass = null;
            if (loader != null) {
                newClass = Class.forName(className, true, loader);
            } else {
                newClass = Class.forName(className);
            }
            Class[] argsClass = new Class[args.length];
            for (int i = 0, j = args.length; i < j; i++) {
                argsClass[i] = args[i].getClass();
            }
            Constructor cons = newClass.getConstructor(argsClass);
            instance = cons.newInstance(args);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }

        return instance;
    }

    public static Object newInstance(String className, Object[] args) {
        return newInstance(className, null, args);
    }

    /**
     * DOC ycbai Comment method "isInstance".
     * 
     * Whether or not the object is the instance of the class.
     * 
     * @param obj
     * @param cls
     * @return
     */
    public static boolean isInstance(Object obj, Class cls) {
        return cls.isInstance(obj);
    }

    /**
     * DOC ycbai Comment method "getByArray".
     * 
     * Returns the value of a element of a array by the index.
     * 
     * @param array
     * @param index
     * @return
     */
    public static Object getByArray(Object array, int index) {
        return Array.get(array, index);
    }
}
