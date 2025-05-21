package org.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.stepdefinitions", "org.hooks"},
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true
)
public class TestRunner3 extends AbstractTestNGCucumberTests {
    // Override this to run scenarios in parallel
    public int scenarioOutlineParallelism() {
        return 3;  // Number of parallel threads to run scenarios
    }
}

