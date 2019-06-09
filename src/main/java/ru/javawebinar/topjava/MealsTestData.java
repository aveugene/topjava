package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class MealsTestData {
    private static final Meal MEAL1 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Девочка Лена", 500);
    private static final Meal MEAL2 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Мыльная пена", 1000);
    private static final Meal MEAL3 = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Крокодил Гена", 500);
    private static final Meal MEAL4 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Телевизионная антенна", 1000);
    private static final Meal MEAL5 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Цирковая арена", 500);
    private static final Meal MEAL6 = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Флакон ацетилена", 510);

    public static final List<Meal> MEALS = Arrays.asList(MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6);

    public static final int CALORIES = 2000;
}

