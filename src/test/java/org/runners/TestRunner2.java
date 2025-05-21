package org.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.stepdefinitions","org.hooks"},
        plugin = {"pretty",},
        monochrome = true
)
public class TestRunner2 {
}
