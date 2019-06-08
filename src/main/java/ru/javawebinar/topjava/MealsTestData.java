package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealsTestData {

    public static List<MealTo> meals = Arrays.asList(
            new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Девочка Лена", 500, false),
            new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Мыльная пена", 1000, false),
            new MealTo(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Крокодил Гена", 500, false),
            new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Телевизионная антенна", 1000, true),
            new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Цирковая арена", 500, true),
            new MealTo(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Флакон ацетилена", 510, true)
    );
}