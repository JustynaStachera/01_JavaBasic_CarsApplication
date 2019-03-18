package com.stachera.justyna.models.converters;

import com.stachera.justyna.json.JsonConverter;
import com.stachera.justyna.models.models.Cars;

/**
 * Developed by Justyna Stachera on 15.01.2019.
 * Package name: com.stachera.justyna.json
 * Last modified 21:17.
 * Copyright (c) 2019. All rights reserved.
 */
public class CarJsonConverter extends JsonConverter<Cars>
{
    public CarJsonConverter(final String jsonFileName)
    {
        super(jsonFileName);
    }
}
