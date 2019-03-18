package com.stachera.justyna.services;

import com.stachera.justyna.exceptions.CustomException;
import com.stachera.justyna.exceptions.ExceptionCode;
import com.stachera.justyna.models.converters.CarJsonConverter;
import com.stachera.justyna.models.enums.Colour;
import com.stachera.justyna.models.enums.Criterion;
import com.stachera.justyna.models.models.Car;
import com.stachera.justyna.models.models.Cars;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Developed by Justyna Stachera on 23.01.2019.
 * Package name: com.stachera.justyna.services
 * Last modified 21:13.
 * Copyright (c) 2019. All rights reserved.
 */
class CarServiceImpl
{
    private final Set<Car> cars;

    private String filePath;

    CarServiceImpl(final String filePath)
    {
        this.filePath = filePath;
        this.cars = new Cars(filePath).getCars();
    }

    Set<Car> getCars()
    {
        return cars;
    }

    void addNewCar(final Car car)
    {
        cars.add(car);

        new CarJsonConverter(filePath).convert(new Cars(cars));
    }

    /**
     * It returns a {@link Car} collection sorted by {@link Criterion}
     * given as an argument. The method sorts by model name, color,
     * price or mileage. Additionally, it should be specified if sorting
     * is descending or ascending.
     *
     * @param criterion sort criterion
     * @param isDesc    descending mode on/off
     * @return {@link Car} collection
     */
    List<Car> sort(Criterion criterion, boolean isDesc)
    {
        List<Car> sortedCars = new ArrayList<>();

        switch (criterion)
        {
            case MODEL:
                sortedCars.addAll(cars.stream().sorted(Comparator.comparing(Car::getPrice))
                        .collect(Collectors.toList()));
                if (isDesc) sortedCars.sort(Comparator.comparing(Car::getPrice).reversed());
                break;
            case PRICE:
                sortedCars.addAll(cars.stream().sorted(Comparator.comparing(Car::getModel))
                        .collect(Collectors.toList()));
                if (isDesc) sortedCars.sort(Comparator.comparing(Car::getPrice).reversed());
            case COLOUR:
                sortedCars.addAll(cars.stream().sorted(Comparator.comparing(Car::getModel))
                        .collect(Collectors.toList()));
                if (isDesc) sortedCars.sort(Comparator.comparing(Car::getPrice).reversed());
                break;
            case MILEAGE:
                sortedCars.addAll(cars.stream().sorted(Comparator.comparing(Car::getModel))
                        .collect(Collectors.toList()));
                if (isDesc) sortedCars.sort(Comparator.comparing(Car::getPrice).reversed());
                break;
        }

        return sortedCars;
    }

    /**
     * It returns a {@link Car} collection which mileage is greater than argument.
     *
     * @param mileage mileage to compare
     * @return {@link Car} collection
     */
    List<Car> getCarsIfMileageGt(Integer mileage)
    {
        return cars.stream().filter(p -> p.getMileage() > mileage).collect(Collectors.toList());
    }

    /**
     * The method returns a map which key is the car model and the value is
     * a {@link Car} object that represents the most expensive car for this model.
     * The map is sorted in descending order.
     *
     * @return model - {@link Car} map
     */
    Map<String, Car> getModelCarMap()
    {
        Map<String, List<Car>> m = cars
                .stream()
                .collect(Collectors.groupingBy(Car::getModel));

        return m.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey, Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .max(Comparator.comparing(Car::getPrice))
                                .orElseThrow(() -> new CustomException(ExceptionCode.CODE_250, "INVALID VALUE")),
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                        )
                );
    }

    /**
     * The method returns the car statistics.
     *
     * @return {@link Car} statistics
     */
    String getStatistics()
    {
        IntSummaryStatistics mileageStatistics = cars.stream().mapToInt(Car::getMileage).summaryStatistics();

        BigDecimal priceMin = cars.stream().min(Comparator.comparing(Car::getPrice)).map(Car::getPrice)
                .orElse(new BigDecimal(0));
        BigDecimal priceMax = cars.stream().min(Comparator.comparing(Car::getPrice)).map(Car::getPrice)
                .orElse(new BigDecimal(0));
        BigDecimal priceAvg = cars.stream().map(Car::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(cars.size()), RoundingMode.HALF_UP);

        String mileageStatisticsStr = MessageFormat
                .format("MileageStatistics[min={0}, max={1}, average={2}]", mileageStatistics.getMin(),
                        mileageStatistics.getMax(), mileageStatistics.getAverage());
        String priceStatisticsStr = MessageFormat
                .format("PriceStatistics[min={0}, max={1}, average={2}]", priceMax, priceAvg, priceMin);

        return mileageStatisticsStr + "\n" + priceStatisticsStr;
    }

    /**
     * It returns the most expensive {@link Car} collection.
     *
     * @return {@link Car} collection
     */
    List<Car> getTheHighestPriceCars()
    {
        BigDecimal maxValue = cars
                .stream()
                .max(Comparator.comparing(Car::getPrice))
                .orElseThrow(() -> new CustomException(ExceptionCode.CODE_250, "VALUE IS NULL"))
                .getPrice();

        return cars.stream().filter(p -> p.getPrice().equals(maxValue)).collect(Collectors.toList());
    }

    /**
     * It returns component name - {@link Car} collection map. The map is sorted
     * descending by {@link Car} collection size.
     *
     * @return component - {@link Car} collection map
     */
    Map<String, List<Car>> getComponentCarsMap()
    {
        Set<String> components = cars.stream().flatMap(car -> car.getComponents().stream()).collect(Collectors.toSet());

        Map<String, List<Car>> componentCarsMap = components
                .stream()
                .collect(Collectors.toMap(
                        comp -> comp,
                        comp -> cars.stream().filter(car -> car.getComponents().contains(comp)).collect(Collectors.toList())
                        )
                );

        return componentCarsMap.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(e -> e.getValue().size()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new)
                );
    }

    /**
     * It returns a {@link Car} collection which price is between lowerPrice argument
     * and upperPrice argument. The collection is sorted by {@link Car} name.
     *
     * @param lowerPrice lower price argument
     * @param upperPrice upper price argument
     * @return {@link Car} collection
     */
    List<Car> getCarsPriceBetween(BigDecimal lowerPrice, BigDecimal upperPrice)
    {
        return cars
                .stream()
                .filter(p -> lowerPrice.compareTo(p.getPrice()) <= 0 && upperPrice.compareTo(p.getPrice()) >= 0)
                .sorted(Comparator.comparing(Car::getModel).reversed())
                .collect(Collectors.toList());
    }

    /**
     * It returns a {@link Colour} - {@link Car} collection size map.
     * The map is sorted descending by value.
     *
     * @return {@link Colour} - {@link Car} collection size
     */
    Map<Colour, Long> getCarAmountByColour()
    {
        return cars
                .stream()
                .collect(Collectors.groupingBy(Car::getColour, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey, Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    /**
     * It returns a {@link Car} collection with the sorted components.
     *
     * @return {@link Car} collection
     */
    List<Car> sortCarComponents()
    {
        return cars
                .stream()
                .peek(car -> car.setComponents(car.getComponents().stream().sorted().collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
