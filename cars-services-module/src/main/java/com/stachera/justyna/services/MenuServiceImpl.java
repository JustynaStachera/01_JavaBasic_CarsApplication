package com.stachera.justyna.services;

import com.stachera.justyna.models.enums.Colour;
import com.stachera.justyna.models.enums.Criterion;
import com.stachera.justyna.models.models.Car;

import java.math.BigDecimal;
import java.util.*;

/**
 * Developed by Justyna Stachera on 23.01.2019.
 * Package name: com.stachera.justyna.services
 * Last modified 21:25.
 * Copyright (c) 2019. All rights reserved.
 */
public class MenuServiceImpl
{
    private CarServiceImpl carService;

    public static void run(final String filePath)
    {
        new MenuServiceImpl(filePath);
    }

    private MenuServiceImpl(final String filePath)
    {
        carService = new CarServiceImpl(filePath);

        runMenu();
    }

    private void runMenu()
    {
        Scanner sc = new Scanner(System.in);

        while (true)
        {
            showMenu();

            String text = sc.nextLine();

            System.out.println();

            switch (text.toLowerCase())
            {
                case "1":
                    addNewCar(sc);
                    break;
                case "2":
                    showData(carService.getCars());
                    break;
                case "3":
                    showData(sort(sc));
                    break;
                case "4":
                    showData(carService.getModelCarMap());
                    break;
                case "5":
                    showData(carService.getStatistics());
                    break;
                case "6":
                    showData(carService.getTheHighestPriceCars());
                    break;
                case "7":
                    showData(getCarsIfMileageGt(sc));
                    break;
                case "8":
                    showData(carService.getCarAmountByColour());
                    break;
                case "9":
                    showData(carService.sortCarComponents());
                    break;
                case "10":
                    showData(carService.getComponentCarsMap());
                    break;
                case "11":
                    showData(getCarsByPriceBetween(sc));
                    break;
                case "x":
                    return;
                default:
                    System.out.println("Wrong option - choose again!");
            }

            System.out.println();
        }
    }

    private List<Car> getCarsIfMileageGt(Scanner sc)
    {
        List<Car> cars = new ArrayList<>();

        try {

            System.out.print("Insert boundary mileage: ");
            Integer mileage = Integer.valueOf(sc.nextLine());
            System.out.println();

            cars.addAll(carService.getCarsIfMileageGt(mileage));
        } catch (IllegalArgumentException e)
        {
            System.err.println("\n" + e.getMessage());
        }

        return cars;
    }

    private List<Car> sort(Scanner sc)
    {
        List<Car> cars = new ArrayList<>();

        try {
            System.out.println("Choose below criterion: ");
            Arrays.asList(Criterion.values()).forEach(System.out::println);
            System.out.print("Insert: ");
            Criterion criterion = Criterion.valueOf(sc.nextLine());

            System.out.print("Do you want descending sort (Y/N)? ");
            boolean isDesc = sc.nextLine().equalsIgnoreCase("Y");
            System.out.println();

            cars.addAll(carService.sort(criterion, isDesc));
        } catch (IllegalArgumentException e)
        {
            System.err.println("\n" + e.getMessage());
        }

        return cars;
    }

    private List<Car> getCarsByPriceBetween(Scanner sc)
    {
        List<Car> cars = new ArrayList<>();

        try {
            System.out.print("Insert lower price: ");
            BigDecimal lowerPrice = new BigDecimal(sc.nextLine());

            System.out.print("Insert upper price: ");
            BigDecimal upperPrice = new BigDecimal(sc.nextLine());
            System.out.println();

            cars.addAll(carService.getCarsPriceBetween(lowerPrice, upperPrice));
        } catch (IllegalArgumentException e)
        {
            System.err.println("\n" + e.getMessage());
        }

        return cars;
    }

    private void addNewCar(Scanner sc)
    {
        try
        {
            System.out.print("Insert model: ");
            String model = sc.nextLine();

            System.out.print("Insert price: ");
            BigDecimal price = new BigDecimal(sc.nextLine());

            System.out.print("Insert colour: ");
            Colour colour = Colour.valueOf(sc.nextLine());

            System.out.print("Insert mileage: ");
            Integer mileage = Integer.valueOf(sc.nextLine());

            System.out.print("Insert components separate with comma: ");
            List<String> components = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));

            Car car = Car
                    .builder()
                    .colour(colour)
                    .components(components)
                    .mileage(mileage)
                    .model(model)
                    .price(price)
                    .build();

            carService.addNewCar(car);
        } catch (IllegalArgumentException e)
        {
            System.err.println("\n" + e.getMessage());
        }
    }

        private void showData(String string)
        {
            System.out.println(string);
        }

        private void showData(Collection<?> set)
        {
            set.forEach(System.out::println);
        }

    private void showData(Map<?, ?> map)
    {
        map.forEach((key, value) -> System.out.println(key + " " + value));
    }

    private void showMenu()
    {
        System.out.println("1) Add new car");
        System.out.println("2) Show all cars");
        System.out.println("3) Sort according to criteria");
        System.out.println("4) Get model car map");
        System.out.println("5) Get statistics");
        System.out.println("6) Get the highest price cars");
        System.out.println("7) Get mileage greater than or equals to...");
        System.out.println("8) Get car amount by colour map");
        System.out.println("9) Sort car components");
        System.out.println("10) Get component cars map");
        System.out.println("11) Get cars by price between...");
        System.out.println("Press X to quit");
        System.out.print("Insert: ");
    }
}
