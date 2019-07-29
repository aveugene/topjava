package ru.javawebinar.topjava.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.TestUtil.readFromJson;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;
import static ru.javawebinar.topjava.util.MealsUtil.createWithExcess;

class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Autowired
    private MealService mealService;

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MealTestData.contentJson(MEAL1));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk());
        assertMatch(mealService.getAll(START_SEQ), MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(MEALSTO));
    }

    @Test
    void createWithLocation() throws Exception {
        Meal created = new Meal(of(2015, Month.MAY, 30, 6, 0), "Ранний завтрак", 300);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());
        Meal returned = readFromJson(action, Meal.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        assertMatch(mealService.getAll(START_SEQ), MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1, created);
    }

    @Test
    void update() throws Exception {
        Meal updated = new Meal(MEAL1_ID, of(2015, Month.MAY, 30, 10, 0), "Обновлённый завтрак", 510);
        ResultActions action = mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertMatch(mealService.get(MEAL1_ID, START_SEQ), updated);
    }

    @Test
    void getBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "between?startDateTime=2015-05-30T00:00:00&endDateTime=2015-05-30T13:00:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(contentJson(List.of(createWithExcess(MEAL2, false), createWithExcess(MEAL1, false))));
    }
}