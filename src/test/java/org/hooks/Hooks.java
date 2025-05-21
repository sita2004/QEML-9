package org.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.zh_cn.假如;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.utils.ConfigReader;
import org.utils.ExtentManager;

public class Hooks {

//    public static WebDriver driver;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static WebDriver getDriver() {
        return driver.get();
    }


    // Extent reports
    private static ExtentReports extent = ExtentManager.getExtentReports();
    public static ExtentTest test;

    @Before
    public void setup(Scenario scenario){
        System.out.println("Opening the browser...");

        String browser = ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("url");

        WebDriver localDriver;
        if(browser.equalsIgnoreCase("chrome")){
//            driver = new ChromeDriver();
            localDriver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("edge")){
//            driver = new EdgeDriver();
            localDriver = new EdgeDriver();
        }
        else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

//        driver.manage().window().maximize();
//        driver.get(url);

        localDriver.manage().window().maximize();
        localDriver.get(url);

        driver.set(localDriver);

        // Create a test in Extent report for each scenario
        test = extent.createTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario){
        System.out.println("Closing the browser");
        if (driver != null){
//            driver.quit();
            driver.get().quit();
            driver.remove();
        }

        // Log pass/fail status in extent report
        if (scenario.isFailed()) {
            test.fail("Scenario Failed");
        } else {
            test.pass("Scenario Passed");
        }

        // Write the report to disk after each scenario
        extent.flush();
    }
}
