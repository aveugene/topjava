package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface Storage {

    Meal save(Meal meal);

    Meal get(int id);

    void delete(int id);

    Collection<Meal> getAll();
}