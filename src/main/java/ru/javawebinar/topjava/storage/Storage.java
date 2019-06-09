package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface Storage {

    void save(Meal meal);

    Meal get(String id);

    void delete(String id);

    Collection<Meal> getAll();
}