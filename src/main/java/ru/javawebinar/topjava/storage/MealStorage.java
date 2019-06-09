package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MealStorage implements Storage {
    private Map<String, Meal> storage = new ConcurrentHashMap<>();

    public MealStorage(List<Meal> storageToAdd) {
        storageToAdd.forEach(meal -> {
            storage.put(meal.getId(), meal);
        });
    }

    @Override
    public void save(Meal meal) {
        storage.put(meal.getId(), meal);
    }

    @Override
    public Meal get(String id) {
        return storage.get(id);
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return storage.values();
    }
}