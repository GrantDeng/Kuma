package com.github.kuma.data.db;

import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class ViewUtils
{
    private ViewUtils() {}

    private static Method getSetterMethod(Class<?> klass, Field fieldType) throws NoSuchMethodException
    {
        // java.beans support is not available on Android :(
        String fieldName = fieldType.getName();
        String capitalized = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        for(Method method: klass.getMethods())
        {
            System.err.println("METHOD: " + method.toString());
        }
        return klass.getMethod("set" + capitalized, fieldType.getType());
    }

    public static Mapper typeMapper(final Class<?> klass) throws ClassNotFoundException
    {
        return ViewUtils.typeMapper(klass, klass.getDeclaredFields());
    }

    public static Mapper typeMapper(final Class<?> klass, final Field[] fields)
    {
        return new Mapper()
        {
            @Override
            public void map(Map<String, Object> document, Emitter emitter)
            {
                try
                {
                    // use reflection to deserialize the object
                    Object modelObject = klass.newInstance();
                    for(Field field: fields)
                    {
                        String fieldName = field.getName();
                        Object value = document.get(fieldName);
                        System.err.println("FIELD: " + fieldName + ", VALUE: " + value);
                        if(value != null)
                        {
                            ViewUtils.getSetterMethod(klass, field).invoke(modelObject, value);
                        }
                    }
                    emitter.emit(document.get("name"), modelObject);
                }
                catch(Exception e)
                {
                    System.err.println("Uh oh");
                    e.printStackTrace();
                }
            }
        };
    }
}
