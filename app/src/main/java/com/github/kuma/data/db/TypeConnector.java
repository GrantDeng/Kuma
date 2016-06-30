package com.github.kuma.data.db;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.support.LazyJsonObject;
import com.github.kuma.db_object.Savable;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TypeConnector is a utility class for handling Couchbase Views.
 */
public final class TypeConnector
{
    private TypeConnector() {}

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
        return klass.getMethod("set" + TypeConnector.getCapitalizedFieldName(fieldType), fieldType.getType());
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
        return klass.getMethod("get" + TypeConnector.getCapitalizedFieldName(fieldType));
    }

    /**
     * Convert a Savable to the corresponding DbDocument.
     * @param context Application context.
     * @param obj The object to convert.
     * @return The created DBDocument.
     * @throws CouchbaseLiteException
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static DbDocument savable2DbDocument(Context context, Savable obj) throws CouchbaseLiteException,
        IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, NullDocumentException
    {
        DbDocument objDoc = new DbDocument(context, obj.getId());
        Class<? extends Savable> objClass = obj.getClass();
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.putAll(objDoc.getProperties());
        for(Field field: TypeConnector.getSavableFields(obj.getClass()))
        {
            try
            {
                Object fieldValue = TypeConnector.getGetterMethod(objClass, field).invoke(obj);
                properties.put(field.getName(), fieldValue);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        objDoc.setProperties(properties);
        return objDoc;
    }

    public static Savable asSavable(Object value) throws ClassNotFoundException, IllegalAccessException,
        InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException
    {
        if(value instanceof Savable)
        {
            return (Savable) value;
        }
        else if(value instanceof LazyJsonObject)
        {
            LazyJsonObject lazyValue = (LazyJsonObject) value;
            // type is something similar to "class com.github.kuma.db_object.Data"
            String className = ((String) lazyValue.get("type")).split(" ")[1];
            Class valueClass = Class.forName(className);
            Savable savableValue = (Savable) valueClass.newInstance();
            for(Object keyObj: lazyValue.keySet())
            {
                String key = (String) keyObj;
                Field correspondingField = TypeConnector.getFieldByName(valueClass, key);
                Method setterMethod = TypeConnector.getSetterMethod(valueClass, correspondingField);
                setterMethod.invoke(savableValue, lazyValue.get(keyObj));
            }
            return savableValue;
        }

        //System.err.println("LAZY BAD");
        return null;
    }

    /**
     * Retrieves a field with the given name from the given class.
     * @param klass The class to retrieve the field from.
     * @param name The name of the field.
     * @return The field, or null if it does not exist.
     */
    private static Field getFieldByName(Class klass, String name)
    {
        for(Field field: TypeConnector.getSavableFields(klass))
        {
            if(field.getName().equals(name))
            {
                return field;
            }
        }
        return null;
    }

    /**
     * Return a Mapper (as in MapReduce) that maps names (document ID's) to Objects of the given type.
     * @param klass Type of the result. Also filters the data we are interested in to just that of the given type.
     * @return The created Mapper.
     * @throws ClassNotFoundException
     */
    public static Mapper typeMapper(final Class<? extends Savable> klass) throws ClassNotFoundException
    {
        Field[] fields = TypeConnector.getSavableFields(klass);
        return TypeConnector.typeMapper(klass, fields);
    }

    /**
     *
     * @param klass
     * @return
     */
    private static List<Field> getNonStaticFields(Class<? extends Savable> klass)
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

    /**
     * Get all the savable fields of a Savable.
     * @param klass The particular class to get savable fields for.
     * @return An array of all savable fields.
     */
    private static Field[] getSavableFields(Class<? extends Savable> klass)
    {
        Class myKlass = (Class) klass;
        Class savableClass = Savable.class;
        List<Field> fields = new LinkedList<Field>();
        fields.addAll(TypeConnector.getNonStaticFields(savableClass));
        final String SAVABLE_NAME = savableClass.getName();
        while(!myKlass.getName().equals(SAVABLE_NAME))
        {
            fields.addAll(TypeConnector.getNonStaticFields(myKlass));
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
    public static Mapper typeMapper(final Class<? extends Savable> klass, final Field[] fields)
    {
        return new Mapper()
        {
            @Override
            public void map(Map<String, Object> document, Emitter emitter)
            {
                try
                {
                    // use reflection to deserialize the object
                    Savable modelObject = klass.newInstance();

                    for(Field field: fields)
                    {
                        String fieldName = field.getName();
                        Object value = document.get(fieldName);

                        if(value != null)
                        {
                            TypeConnector.getSetterMethod(klass, field).invoke(modelObject, value);
                        }
                    }

                    String id = modelObject.getId();
                    assert(id != null);
                    emitter.emit(id, modelObject);
                }
                catch(Exception e)
                {
                    //System.err.println("Uh oh");
                    e.printStackTrace();
                }
            }
        };
    }
}
