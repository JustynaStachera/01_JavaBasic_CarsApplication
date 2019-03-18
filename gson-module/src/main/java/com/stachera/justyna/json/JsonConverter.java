package com.stachera.justyna.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Developed by Justyna Stachera on 15.01.2019.
 * Package name: com.stachera.justyna.json
 * Last modified 21:08.
 * Copyright (c) 2019. All rights reserved.
 */
public abstract class JsonConverter<T>
{
    private final String jsonFileName;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    
    public JsonConverter(String jsonFileName)
    {
        this.jsonFileName = jsonFileName;
    }
    
    public void convert(final T obj)
    {
        try (FileWriter fileWriter = new FileWriter(jsonFileName))
        {
            if (obj == null)
            { throw new NullPointerException("NULL VALIDATION"); }
            
            gson.toJson(obj, fileWriter);
        }
        catch (NullPointerException | IOException e)
        {
            System.err.println(e);
        }
    }
    
    public T convert() {
        try (FileReader fileReader = new FileReader(jsonFileName)) {
            return gson.fromJson(fileReader, type);
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
        
        return null;
    }
}
