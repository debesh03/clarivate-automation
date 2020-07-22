package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

/**
 * Responsible to locate elements and perform functions on Search Results Page
 */

public class SearchResultsPage extends BasePage{

	
    @FindBy(xpath = "(//cite)[1]")
    WebElement firstSearchResult;

    @FindBy(css = "div#result-stats")
    WebElement searchResultCount;
    /**
     * Initialize all the elements on Search Results page
     * @param driver the Webdriver object
     */
    public SearchResultsPage(WebDriver driver) {
    	super(driver);
    }

    /**
     * Get the url of first search result on Google Search page
     * @return firstSearchResult the WebElement representing the first search result
     */
    public String getFirstSearchResult(){
    	getLogger().info("Waiting for element to load in serach result");
    	WebElement element = waitForElement(driver, 10, searchResultCount);
    	getLogger().info("Fetching first result in google search result : {}", element);
        return firstSearchResult.getText();
    }
}
