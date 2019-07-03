package ru.javawebinar.topjava;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class LogRule extends TestWatcher {
    private final Logger log = getLogger(LogRule.class);
    private long millis = 0;
    public static List<String> REPORT = new ArrayList<>();

    @Override
    protected void starting(Description description) {
        log.debug("Тест " + description.getDisplayName() + " начинается.");
        millis = System.currentTimeMillis();
    }

    @Override
    protected void finished(Description description) {
        long timeElapsed = System.currentTimeMillis() - millis;
        String logLine = "Тест " + description.getDisplayName() + " закончен. Время затрачено " + timeElapsed + "мс.";
        log.debug(logLine);
        REPORT.add(logLine);
    }
}