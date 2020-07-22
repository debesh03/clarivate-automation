package steps;

import org.junit.Assert;
import base.BaseUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


/**
 * Steps for Google Search
*/

public class GoogleSearchSteps extends BaseUtil{
	
	BaseUtil testConext;

	public GoogleSearchSteps(BaseUtil testConext) {
		this.testConext = testConext;
	}

	@Given("I am on chrome browser")
	public void i_open_Chrome_Browser() {
		testConext.setupBrowser("chrome");
		getLogger().info("Browser launced");
	}

	@When("I navigate to {string}")
	public void i_navigate_to(String url) {
		testConext.getDriver().get(url);
		getLogger().info("Navigated to url : {}", url);
	}

	@When("I search for {string}")
	public void i_search_for(String text) {
		testConext.getGoogleSearchPage().searchForKeyword(text);
		getLogger().info("Search Keyword {} found ", text);
	}

	@Then("first search result should be {string}")
	public void first_search_result_should_be(String expectedResult) {
		String firstResult = testConext.getSearchResultsPage().getFirstSearchResult();
		getLogger().info("First search result found : {}", firstResult);
		Assert.assertEquals("First search result is not matching",expectedResult, firstResult);
	}

}
