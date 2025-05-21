//package org.stepdefinitions;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.hooks.Hooks;
//import org.junit.Assert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class SearchProductSteps {
//
//    WebDriver driver = Hooks.driver;
//
//    @Given("I am on the product search page")
//    public void navigateToProductSearchPage() {
//        System.out.println("Navigating to the product search page.");
//    }
//
//
//    @When("I search for {string}")
//    public void searchForProduct(String productName) throws InterruptedException {
//        System.out.println("Searching for product: " + productName);
////        driver.findElement(By.id("search")).sendKeys(productName); // Replace with correct element selector
////        driver.findElement(By.xpath("//button[text()='search']")).click();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("search"))).sendKeys(productName);
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='search']"))).click();
//    }
//
//    @Then("I should see search results containing {string}")
//    public void verifySearchResultsContain(String productName) {
//        System.out.println("Displaying search results containing: " + productName);
//        System.out.println(driver.getCurrentUrl());
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        WebElement searchHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-test = 'lp-resultsCount']/span")));
//        String text = searchHeader.getText();
//        Assert.assertEquals(productName,text);
//    }
//}


package org.stepdefinitions;

import com.aventstack.extentreports.MediaEntityBuilder;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.hooks.Hooks;
//import org.hooks.Hooks2;
import io.qameta.allure.Description;
import jdk.jfr.Label;
import org.hooks.Hooks;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class SearchProductSteps {

    WebDriver driver = Hooks.getDriver(); // Hook provides WebDriver initialization

    @Description("This test verifies the search functionality by searching a product and validating the results.")
    @Given("I am on the product search page")
    public void navigateToProductSearchPage() {
        System.out.println("Navigating to the product search page.");
        Hooks.test.info("Navigated to the product search page");
        attachExampleData("Navigated to the product search page - confirmation text.");
    }

    @Description("This step searches for the product with the given name.")
    @When("I search for {string}")
    public void searchForProduct(String productName) {
        System.out.println("Searching for product: " + productName);
        Hooks.test.info("Searching for product: " + productName);
        driver.findElement(By.id("search")).sendKeys(productName);
        driver.findElement(By.xpath("//button[text()='search']")).click();

        // Adding a screenshot to Allure
        attachScreenshot(driver, "Screenshot after searching for: " + productName);

        Hooks.test.info("Screenshot after searching for: " + productName,
                MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotBase64()).build());
    }

    @Description("This step verifies that search results contain the searched product name.")
    @Then("I should see search results containing {string}")
    public void verifySearchResultsContain(String productName) {
        System.out.println("Displaying search results containing: " + productName);
        System.out.println(driver.getCurrentUrl());

        Hooks.test.info("Verifying search results containing: " + productName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-test = 'lp-resultsCount']/span")));
        String text = searchHeader.getText();

        // Adding another screenshot after verifying results
        attachScreenshot(driver, "Screenshot after verifying results containing: " + productName);

        Hooks.test.info("Screenshot after searching for: " + productName,
                MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotBase64()).build());
        Assert.assertEquals(productName, text);

    }

/*    @Attachment(value = "{description}", type = "image/png")
    public byte[] attachScreenshot(WebDriver driver, String description) {
//        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        System.out.println("Screenshot taken for: " + description);
        return screenshot;
    }

    @Attachment(value = "Debugging Data: Example Context", type = "text/plain")
    public String attachExampleData(String data) {
        return data;
    }
 */

    /**
     * Attach a screenshot to Allure report
     * @param driver WebDriver instance
     * @param description Description of the screenshot
     */
    public void attachScreenshot(WebDriver driver, String description) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(description, new ByteArrayInputStream(screenshot));
        System.out.println("Screenshot attached: " + description);
    }

    /**
     * Attach text data to Allure report
     * @param data Some debug text
     */
    public void attachExampleData(String data) {
        Allure.addAttachment("Debugging Data: Example Context",
                "text/plain", data, ".txt");
        System.out.println("Debug data attached: " + data);
    }

    // Helper to get screenshot in base64 format for extent report
    public String getScreenshotBase64() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
}

