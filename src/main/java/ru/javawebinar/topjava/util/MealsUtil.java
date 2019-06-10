package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;

public class MealsUtil {

    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(LocalDateTime.of(2019, Month.MAY, 25, 10, 0), "Мальчик Миша", 500),
            new Meal(LocalDateTime.of(2019, Month.MAY, 25, 13, 0), "Бочка гашиша", 1000),
            new Meal(LocalDateTime.of(2019, Month.MAY, 25, 20, 0), "Девочка Зина", 502),
            new Meal(LocalDateTime.of(2019, Month.MAY, 22, 10, 0), "Часовая мина", 1000),
            new Meal(LocalDateTime.of(2019, Month.MAY, 12, 13, 0), "Болотная тина", 500),
            new Meal(LocalDateTime.of(2019, Month.MAY, 22, 20, 0), "Дырявая шина", 510)
    );
    public static final int CALORIES = 2000;

    public static List<MealTo> getFilteredWithExcessInOnePass2(Collection<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final class Aggregate {
            private final List<Meal> dailyMeals = new ArrayList<>();
            private int dailySumOfCalories;

            private void accumulate(Meal meal) {
                dailySumOfCalories += meal.getCalories();
                if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                    dailyMeals.add(meal);
                }
            }

            // never invoked if the upstream is sequential
            private Aggregate combine(Aggregate that) {
                this.dailySumOfCalories += that.dailySumOfCalories;
                this.dailyMeals.addAll(that.dailyMeals);
                return this;
            }

            private Stream<MealTo> finisher() {
                final boolean excess = dailySumOfCalories > caloriesPerDay;
                return dailyMeals.stream().map(meal -> createWithExcess(meal, excess));
            }
        }

        Collection<Stream<MealTo>> values = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate,
                        Collector.of(Aggregate::new, Aggregate::accumulate, Aggregate::combine, Aggregate::finisher))
                ).values();

        return values.stream().flatMap(identity()).collect(toList());
    }

    private static MealTo createWithExcess(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}