package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MemoryStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
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
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = new MemoryStorage();
        MealsUtil.MEALS.forEach(meal -> storage.save(meal));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");

        switch (action == null ? "default" : action) {
            case "delete":
                int id = getId(request);
                log.debug("Delete meal with id " + id);
                storage.delete(id);
                response.sendRedirect("meals");
                return;
            case "add":
            case "edit":
                request.setAttribute("meal", action.equals("add") ? new Meal(LocalDateTime.now(), "", 0) : storage.get(getId(request)));
                request.getRequestDispatcher("/edit.jsp").forward(request, response);
                break;
            default:
                request.setAttribute("meals", MealsUtil.getFilteredWithExcessInOnePass2(storage.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        int id = getId(request);
        String datetime = request.getParameter("datetime");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");
        log.debug("Add/Edit meal with id " + id);
        storage.save(new Meal(id, stringToDate(datetime), description, Integer.parseInt(calories)));
        response.sendRedirect("meals");
    }
}