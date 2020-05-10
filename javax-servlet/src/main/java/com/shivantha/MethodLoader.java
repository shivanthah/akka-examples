package com.shivantha;

import org.reflections.Reflections;

import java.lang.reflect.Method;

public class MethodLoader {
    public static Method getMethod(String path) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Reflections ref = new Reflections("com.shivantha");
        for (Class<?> cl : ref.getTypesAnnotatedWith(MyController.class)) {
            for (Method method : cl.getDeclaredMethods()) {
                MyPath myPath = method.getAnnotation(MyPath.class);
                if (myPath.path().equals(path)) {
                    return method;
                }
            }
        }
        throw new NoSuchMethodError("No method define for path " + path);
    }
}
