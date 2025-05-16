package stepDefinitions;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.cucumber.java.en.*;

import static org.testng.Assert.assertTrue;

public class SearchProductSteps {

    @Step("User is on the homepage")
    @Given("the user is on the homepage")
    public void user_is_on_homepage() {
        System.out.println("User is on the homepage.");
    }

    @Step("User searches for: {productName}")
    @When("the user searches for {string}")
    public void the_user_searches_for(String productName) {
        logSearchQuery(productName);
        System.out.println("Searching for: " + productName);
    }

    @Step("Verify search results for: {productName}")
    @Then("relevant results including {string} should be displayed")
    public void relevant_results_including_should_be_displayed(String productName) {
        System.out.println("Displaying search results for: " + productName);
        attachText("Search Result", "Results found for: " + productName);
    }

    @Attachment(value = "{name}", type = "text/plain")
    public String attachText(String name, String content) {
        return content;
    }

    @Attachment(value = "Search Query", type = "text/plain")
    public String logSearchQuery(String query) {
        return "Searched for: " + query;
    }
}
