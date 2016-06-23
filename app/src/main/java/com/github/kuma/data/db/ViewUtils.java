package com.github.kuma.data.db;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;
import com.github.kuma.db_object.Savable;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ViewUtils is a utility class for handling Couchbase Views.
 */
public final class ViewUtils
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
        return klass.getMethod("set" + ViewUtils.getCapitalizedFieldName(fieldType), fieldType.getType());
    }

    /**
     * Returns a capitalized field name.
     * @param fieldType Field name to capitalize.
     * @return The capitalized field name.
     */
    private static String getCapitalizedFieldName(Field fieldType)
    {
        String fieldName = fieldType.getName();
        return Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
    }

    /**
     * Retrieves a JavaBean-style getter method for a given class that acts on a certain field.
     * Necessary because Android does not support the full java.beans package.
     * @param klass The class with the getter method.
     * @param fieldType The field the getter acts on.
     * @return The getter method.
     * @throws NoSuchMethodException
     */
    private static Method getGetterMethod(Class<?> klass, Field fieldType)
        throws NoSuchMethodException
    {
        return klass.getMethod("get" + ViewUtils.getCapitalizedFieldName(fieldType));
    }

    public static DbDocument savable2DbDocument(Context context, Savable obj) throws CouchbaseLiteException,
        IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        DbDocument objDoc = new DbDocument(context, obj.getId());
        Class<? extends Savable> objClass = obj.getClass();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.putAll(objDoc.getProperties());
        for(Field field: ViewUtils.getSavableFields(obj.getClass()))
        {
            Object fieldValue = ViewUtils.getGetterMethod(objClass, field).invoke(obj);
            properties.put(field.getName(), fieldValue);
        }
        objDoc.setProperties(properties);
        return objDoc;
    }

    /**
     * Return a Mapper (as in MapReduce) that maps names (document ID's) to Objects of the given type.
     * @param klass Type of the result. Also filters the data we are interested in to just that of the given type.
     * @return The created Mapper.
     * @throws ClassNotFoundException
     */
    public static Mapper typeMapper(final Class<? extends Savable> klass) throws ClassNotFoundException
    {
        Field[] fields = ViewUtils.getSavableFields(klass);
        return ViewUtils.typeMapper(klass, fields);
    }

    private static List<Field> getNonStaticFields(Class klass)
    {
        List<Field> fields = new LinkedList<Field>();
        for(Field field: klass.getDeclaredFields())
        {
            if(!Modifier.isStatic(field.getModifiers()))
            {
                fields.add(field);
            }
        }
        return fields;
    }

    private static Field[] getSavableFields(Class<? extends Savable> klass)
    {
        Class myKlass = (Class) klass;
        Class savableClass = Savable.class;
        List<Field> fields = new LinkedList<Field>();
        fields.addAll(ViewUtils.getNonStaticFields(savableClass));
        final String SAVABLE_NAME = savableClass.getName();
        while(!myKlass.getName().equals(SAVABLE_NAME))
        {
            fields.addAll(ViewUtils.getNonStaticFields(myKlass));
            myKlass = myKlass.getSuperclass();
        }
        return fields.toArray(new Field[0]);
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
