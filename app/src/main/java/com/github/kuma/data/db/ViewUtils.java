package com.github.kuma.data.db;

import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;

import java.util.Map;

public class ViewUtils
{
    private ViewUtils() {}

    public static Mapper dummyMapper()
    {
        return new Mapper()
        {
            @Override
            public void map(Map<String, Object> document, Emitter emitter)
            {
                emitter.emit(document.get("name"), null);
            }
        };
    }
}
