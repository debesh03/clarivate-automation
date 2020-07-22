package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.BasePage;
/**
 * Locate elements and perform actions on Google Search HomePage
 */
public class GoogleSearchPage extends BasePage{
	
	
    @FindBy(name = "q")
    WebElement txtSearchBox;

    /**
     * Initialize all the elements on Google Search home page
     * @param driver the Webdriver object
     */
    public GoogleSearchPage(WebDriver driver) {
    	super(driver);
    }

    /**
     * Search a keyword on Google Search page
     * @param keyword the String need to be searched
     */
    public void searchForKeyword(String keyword){
    	getLogger().info("Entering text in searchbox : {}",keyword);
    	txtSearchBox.sendKeys(keyword);
    	getLogger().info("Submiting the search text");
    	txtSearchBox.submit();
    }
}
