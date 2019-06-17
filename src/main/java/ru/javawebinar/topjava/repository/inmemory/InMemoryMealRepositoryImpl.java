package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, authUserId()));
        MealsUtil.MEALS.forEach(meal -> save(new Meal(1, LocalDateTime.of(2015, Month.SEPTEMBER, 9, 13, 40), "sdfdf", 205), 50));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
         // 2.1 sneaky user tries to change someone's food:
        } else if (get(meal.getId(), userId) == null) {
            return null;
        }
        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, m -> new ConcurrentHashMap<>());
        meals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals != null && userMeals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals != null ? userMeals.get(id) : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAllSortedStream(userId)
                    .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getFiltered(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return getAllSortedStream(userId)
                    .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), startDate, endDate))
                    .collect(Collectors.toList());
    }

    private Stream<Meal> getAllSortedStream(int userId){
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals == null ? Stream.empty() : userMeals.values().stream().sorted();
    }
}