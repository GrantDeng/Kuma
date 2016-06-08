package com.github.kuma.data.db;

import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;

import java.util.Map;
import java.util.Set;

public class ViewUtils
{
    private ViewUtils() {}

    public final static String DB_TYPE_KEY = "objectType";

    public static Mapper dataTypeMapper(final String dataType, final Set<String> keys)
    {
        return new Mapper()
        {
            @Override
            public void map(Map<String, Object> document, Emitter emitter)
            {
                if(!document.get(ViewUtils.DB_TYPE_KEY).equals(dataType))
                {
                    return;
                }
                for(String key: keys)
                {
                    emitter.emit(key, document.get(key));
                }
            }
        };
    }
}
