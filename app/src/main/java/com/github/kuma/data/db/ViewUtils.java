package com.github.kuma.data.db;

import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * ViewUtils is a utility class for handling Couchbase Views.
 */
public class ViewUtils
{
    private ViewUtils() {}

    /**
     * Retrieves a JavaBean-style setter method for a given class that acts on a certain field.
     * Necessary because Android does not support the full java.beans package.
     * @param klass The class with the setter method.
     * @param fieldType The field the setter acts on.
     * @return The setter method.
     * @throws NoSuchMethodException
     */
    private static Method getSetterMethod(Class<?> klass, Field fieldType) throws NoSuchMethodException
    {
        String fieldName = fieldType.getName();
        String capitalized = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

        for(Method method: klass.getMethods())
        {
            System.err.println("METHOD: " + method.toString());
        }

        return klass.getMethod("set" + capitalized, fieldType.getType());
    }

    /**
     * Return a Mapper (as in MapReduce) that maps names (document ID's) to Objects of the given type.
     * @param klass Type of the result. Also filters the data we are interested in to just that of the given type.
     * @return The created Mapper.
     * @throws ClassNotFoundException
     */
    public static Mapper typeMapper(final Class<?> klass) throws ClassNotFoundException
    {
        return ViewUtils.typeMapper(klass, klass.getDeclaredFields());
    }

    /**
     * Return a Mapper (as in MapReduce) that maps names (document ID's) to Objects of the given type.
     * Only the fields we are interested in are filled in.
     * @param klass Type of the result. Also filters the data we are interested in to just that of the given type.
     * @param fields The fields we are interested in.
     * @return The created Mapper.
     */
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
