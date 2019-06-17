package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);

            List<MealTo> allMeals = mealRestController.getAll();
            allMeals.forEach(System.out::println);

            List<MealTo> filteredMeals = mealRestController.getFiltered(
                    LocalDate.of(2015, Month.MAY, 30),
                    LocalDate.of(2015, Month.MAY, 30),
                    LocalTime.MIN,
                    LocalTime.MAX
            );
            filteredMeals.forEach(System.out::println);
            System.out.println();
            System.out.println();
            mealRestController.update(new Meal(LocalDateTime.of(2014, Month.SEPTEMBER, 9, 13, 40), "sdfdf", 205), 3);

            Meal meal = mealRestController.get(3);
            System.out.println(meal);
        }
    }
}
