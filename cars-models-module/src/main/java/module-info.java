/**
 * Developed by Justyna Stachera on 25.02.2019.
 * Package name:
 * Last modified 18:51.
 * Copyright (c) 2019. All rights reserved.
 */module cars.model.module {
     requires gson.module;

     exports com.stachera.justyna.models.models;
     exports com.stachera.justyna.models.enums;
     exports com.stachera.justyna.models.converters;

     opens com.stachera.justyna.models.models;
}
