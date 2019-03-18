package com.stachera.justyna.app;

import com.stachera.justyna.services.MenuServiceImpl;

/**
 * Developed by Justyna Stachera on 21.01.2019.
 * Package name: com.stachera.justyna
 * Last modified 21:37.
 * Copyright (c) 2019. All rights reserved.
 */
public class CarsApp
{
    private static final String CARS_SOURCE_FILE = "/cars-app/src/main/resources/cars.json";

    public static void main(String[] args)
    {
        MenuServiceImpl.run(CARS_SOURCE_FILE);
    }
}
