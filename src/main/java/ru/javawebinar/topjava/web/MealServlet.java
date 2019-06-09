package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.MealsTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.TimeUtil.stringToDate;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private Storage storage = new MealStorage(MealsTestData.MEALS);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String id = request.getParameter("id");
        String action = request.getParameter("action");

        switch (action == null ? "default" : action) {
            case "delete":
                storage.delete(id);
                response.sendRedirect("meals");
                return;
            case "add":
            case "edit":
                request.setAttribute("meal", id == null ? new Meal(LocalDateTime.now(), "", 0) : storage.get(id));
                request.getRequestDispatcher("/edit.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("meals", MealsUtil.getFilteredWithExcessInOnePass2(storage.getAll(), LocalTime.MIN, LocalTime.MAX, MealsTestData.CALORIES));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String datetime = request.getParameter("datetime");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");
        storage.save(new Meal(id, stringToDate(datetime), description, Integer.parseInt(calories)));
        response.sendRedirect("meals");
    }
}