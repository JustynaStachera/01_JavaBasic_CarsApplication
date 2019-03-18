package com.stachera.justyna.models.models;

import com.stachera.justyna.models.enums.Colour;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Developed by Justyna Stachera on 15.01.2019.
 * Package name: com.stachera.justyna.models
 * Last modified 20:09.
 * Copyright (c) 2019. All rights reserved.
 */
public class Car
{
    private String model;
    private BigDecimal price;
    private Colour colour;
    private Integer mileage;
    private List<String> components;

    public Car()
    {
    }

    private Car(CarBuilder carBuilder)
    {
        this.model = carBuilder.model;
        this.colour = carBuilder.colour;
        this.components = carBuilder.components;
        this.mileage = carBuilder.mileage;
        this.price = carBuilder.price;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public Colour getColour()
    {
        return colour;
    }

    public void setColour(Colour colour)
    {
        this.colour = colour;
    }

    public Integer getMileage()
    {
        return mileage;
    }

    public void setMileage(Integer mileage)
    {
        this.mileage = mileage;
    }

    public List<String> getComponents()
    {
        return components;
    }

    public void setComponents(List<String> components)
    {
        this.components = components;
    }

    public static CarBuilder builder()
    {
        return new CarBuilder();
    }

    @Override
    public String toString()
    {
        return "Car{" +
               "model='" + model + '\'' +
               ", price=" + price +
               ", colour=" + colour +
               ", mileage=" + mileage +
               ", components=" + components +
               '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(model, car.model) &&
                Objects.equals(price, car.price) &&
                colour == car.colour &&
                Objects.equals(mileage, car.mileage) &&
                Objects.equals(components, car.components);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(model, price, colour, mileage, components);
    }

    public static class CarBuilder
    {
        private String model;
        private BigDecimal price;
        private Colour colour;
        private Integer mileage;
        private List<String> components;
        
        public CarBuilder model(String model)
        {
            if (model != null && model.matches("^[A-Z\\s]+$"))
            { this.model = model; }
            else
            { System.err.println("MODEL VALIDATION"); }
            
            return this;
        }
        
        public CarBuilder price(BigDecimal price)
        {
            if (price != null && price.doubleValue() > 0)
            { this.price = price; }
            else
            { System.err.println("PRICE VALIDATION"); }
    
            return this;
        }
        
        public CarBuilder colour(Colour colour)
        {
            this.colour = colour;
    
            return this;
        }
        
        public CarBuilder mileage(Integer mileage)
        {
            if (mileage != null && mileage > 0)
            { this.mileage = mileage; }
            else
            { System.err.println("MILEAGE VALIDATION"); }
    
            return this;
        }
        
        public CarBuilder components(List<String> components)
        {
            if (components.stream().allMatch(p -> p.matches("^[A-Z\\s]+$")))
            { this.components = components; }
            else
            { System.err.println("COMPONENTS VALIDATION"); }
    
            return this;
        }
        
        public Car build()
        {
            return new Car(this);
        }
    }
}
