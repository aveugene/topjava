package ru.javawebinar.topjava;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static ru.javawebinar.topjava.LogRule.REPORT;

public class ReportRule extends TestWatcher {

    @Override
    protected void finished(Description description) {
        System.out.println("\nИтоговый отчёт по времени:");
        REPORT.forEach(System.out::println);
        System.out.println("\n");
    }
}