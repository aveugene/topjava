package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collector;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        System.out.println(getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    private static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesMap = new HashMap<>();
        mealList.forEach(meal -> caloriesMap.merge(meal.getDate(), meal.getCalories(), Integer::sum));

        List<UserMealWithExceed> mealExceedList = new ArrayList<>();
        mealList.forEach(meal -> {
            if (TimeUtil.isBetween(meal.getTime(), startTime, endTime)) {
                mealExceedList.add(toMealWithExceed(meal, caloriesMap.get(meal.getDate()) > caloriesPerDay));
            }
        });
        return mealExceedList;
    }

    private static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesMap = new HashMap<>();
        return mealList.stream()
                .collect(Collector.of(
                        ArrayList::new,
                        (previousList, userMealToAdd) -> {
                            caloriesMap.put(userMealToAdd.getDate(), caloriesMap.getOrDefault(userMealToAdd.getDate(), 0) + userMealToAdd.getCalories());
                            if (mealList.get(mealList.size() - 1).equals(userMealToAdd)) {
                                previousList.forEach(userMealWithExceed -> {
                                    userMealWithExceed.setExceed(caloriesMap.get(userMealWithExceed.getDate()) > caloriesPerDay);
                                });
                            }
                            if (TimeUtil.isBetween(userMealToAdd.getTime(), startTime, endTime)) {
                                previousList.add(toMealWithExceed(userMealToAdd, caloriesMap.get(userMealToAdd.getDate()) > caloriesPerDay));
                            }
                        },
                        (leftList, rightList) -> {
                            leftList.addAll(rightList);
                            return leftList;
                        }
                ));

        /*
        Map<LocalDate, Integer> caloriesMap = mealList.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories)));
        return mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> toMealWithExceed(meal, caloriesMap.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
        */
    }

    private static UserMealWithExceed toMealWithExceed(UserMeal meal, boolean exceed) {
        return new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed);
    }
}
