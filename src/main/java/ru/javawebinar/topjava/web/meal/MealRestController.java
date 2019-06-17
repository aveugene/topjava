package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);
    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id) {
        int userId = authUserId();
        log.info("get meal of id {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = authUserId();
        log.info("delete meal of id {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public Meal create(Meal meal) {
        int userId = authUserId();
        log.info("create meal {} for user {}", meal, userId);
        checkNew(meal);
        return service.create(meal, userId);
    }

    public void update(Meal meal, int id) {
        int userId = authUserId();
        log.info("update meal {} for user {}", meal, userId);
        assureIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public List<MealTo> getAll() {
        int userId = authUserId();
        log.info("getAll meals for user {}", userId);
        return MealsUtil.getWithExcess(service.getAll(userId), authUserCaloriesPerDay());
    }

    public List<MealTo> getFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        int userId = authUserId();
        log.info("get filtered meals for user {} between {} {} and {} {}", userId, startDate, startTime, endDate, endTime);
        return MealsUtil.getFilteredWithExcess(
                service.getFiltered(
                        startDate != null ? LocalDateTime.of(startDate, LocalTime.MIN) : LocalDateTime.of(1, Month.SEPTEMBER, 1, 0, 0),
                        endDate != null ? LocalDateTime.of(endDate, LocalTime.MAX) : LocalDateTime.of(9999, Month.SEPTEMBER, 1, 0, 0),
                        userId),
                authUserCaloriesPerDay(),
                startTime == null ? LocalTime.MIN : startTime,
                endTime == null ? LocalTime.MAX : endTime
        );
    }
}