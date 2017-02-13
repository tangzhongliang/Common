package jp.co.ricoh.advop.mini.cheetahminiutil.tzl;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;


/**
 * Created by leonard on 16-9-4.
 */
public class RefInvoke {

    private HashMap<String, Method> methodMap = new HashMap<String, Method>();
    private HashMap<String, Field> fieldMap = new HashMap<String, Field>();

    public static Object invokeStaticMethod(String s, String methodName, Class[] classes, Object[] values) {
        Object ret = null;
        try {
            Class<?> aClass = Class.forName(s);
            Method method = getMethod(aClass, methodName, classes);
            ret = method.invoke(values);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static Object invokeMethod(Object o, String methodName, Class[] types, Object[] values) {
        Object ret = null;
        Method method = null;
        try {
            Class<?> aClass = o.getClass();
            method = getMethod(aClass, methodName, types);
            ret = method.invoke(o, values);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (method != null) {
                method.setAccessible(false);
            }
        }
        return ret;
    }

    public static Object getFieldObject(Object object, String fieldName) {
        Field declaredField = null;
        try {
            declaredField = getField(object.getClass(), fieldName);
            Object o = declaredField.get(object);
            return o;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (declaredField != null) {
                declaredField.setAccessible(false);
            }
        }
        return null;
    }

    public static void setFieldObject(String fieldName, Object o, Object value) {
        Field declaredField = null;
        try {
            declaredField = getField(o.getClass(), fieldName);
            declaredField.set(o, value);
        } catch (IllegalAccessException e) {
        } finally {
            if (declaredField != null) {
                declaredField.setAccessible(false);
            }
        }
    }

    public static void setStaticFieldObject(String fieldName, String clsName, Object value) {
        Field declaredField = null;
        try {
            Class<?> aClass = Class.forName(clsName);
            declaredField = getField(aClass, fieldName);
            declaredField.set(aClass, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (declaredField != null) {
                declaredField.setAccessible(false);
            }
        }
    }

    public static Constructor getConstructor(Class cls, Class[] types) {
        Constructor declaredMethod = null;
        try {
            declaredMethod = cls.getConstructor(types);
            return declaredMethod;
        } catch (NoSuchMethodException e) {
        } finally {
            if (declaredMethod == null && cls.getSuperclass() != null)
                return getConstructor(cls.getSuperclass(), types);
        }
        return declaredMethod;
    }

    public static Method getMethod(Class cls, String methodName, Class[] types) {
        Method declaredMethod = null;
        try {
            declaredMethod = cls.getDeclaredMethod(methodName, types);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException e) {
        } finally {
            if (declaredMethod == null && cls.getSuperclass() != null)
                return getMethod(cls.getSuperclass(), methodName, types);
        }
        return declaredMethod;
    }

    public static Field getField(Class cls, String fieldName) {
        Field field = null;
        try {
            field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
        } finally {
            if (field == null && cls.getSuperclass() != null)
                return getField(cls.getSuperclass(), fieldName);
        }
        return field;
    }

    public static Field getField(String clsName, String fieldName) {
        Class<?> aClass = null;
        try {
            aClass = Class.forName(clsName);
        } catch (ClassNotFoundException e) {
            return null;
        }
        return getField(aClass, fieldName);
    }
}
