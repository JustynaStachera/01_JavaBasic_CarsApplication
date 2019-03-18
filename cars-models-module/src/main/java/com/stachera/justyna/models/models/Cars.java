package com.stachera.justyna.models.models;

import com.stachera.justyna.models.converters.CarJsonConverter;

import java.io.File;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * Developed by Justyna Stachera on 15.01.2019.
 * Package name: com.stachera.justyna.models
 * Last modified 21:03.
 * Copyright (c) 2019. All rights reserved.
 */
public class Cars
{
    private Set<Car> cars;

    public Cars()
    {
    }

    public Cars(Set<Car> cars)
    {
        this.cars = cars;
    }

    public Cars(final String filePath)
    {
        cars = new CarJsonConverter(prepareFilePath(filePath)).convert().getCars();
    }

    public Set<Car> getCars()
    {
        return cars;
    }

    public void setCars(Set<Car> cars)
    {
        this.cars = cars;
    }

    @Override
    public String toString()
    {
        return cars.stream().map(Car::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars1 = (Cars) o;
        return Objects.equals(cars, cars1.cars);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(cars);
    }

    private String prepareFilePath(final String filePath)
    {
        return new File("")
                .getAbsolutePath()
                .concat(filePath.replaceAll("/", Matcher.quoteReplacement(File.separator)));
    }
}
