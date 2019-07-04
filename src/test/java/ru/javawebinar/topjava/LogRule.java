package ru.javawebinar.topjava;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class LogRule extends TestWatcher {
    public static final Logger LOG = getLogger(LogRule.class);
    private long start = 0;
    public static List<String> REPORT = new ArrayList<>();

    @Override
    protected void starting(Description description) {
        start = System.currentTimeMillis();
    }

    @Override
    protected void finished(Description description) {
        long timeElapsed = System.currentTimeMillis() - start;
        String logLine = String.format("Test %s elapsed %dms.", description.getMethodName(), timeElapsed);
        LOG.debug(logLine);
        REPORT.add(logLine);
    }
}