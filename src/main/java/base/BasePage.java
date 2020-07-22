package base;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.WiniumDriver;


/**
 * Parent class for all page object class.Defines the generic method to handle web elements
 *
 */
public abstract class BasePage extends BaseUtil {
	
	
	/**
	 * Intialize the child class elements using PageFactory at runtime while creating the instance of child class 
	 * @param driver the WebDriver instance
	 */
	public BasePage(WebDriver driver){
		
		if(driver instanceof WiniumDriver){
			super.winiumDriver = driver;
		}
		else {
			super.driver = driver;
		}
		PageFactory.initElements(driver, this);
	}
	
	
	/**
	 * Generic method to wait till the element is visible
	 * @param driver the WebDriver instance
	 * @param timeOutInSeconds seconds to wait for element
	 * @param element the WebElement to wait for visible
	 * @return
	 */
	public WebElement waitForElement(WebDriver driver, long timeOutInSeconds, WebElement element) {
		try {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		getLogger().info("WebElement located successfully");
		} catch(TimeoutException ex) {
			getLogger().error("Unable to find the element within expected time : {}", ex);
	    }catch(Exception ex) {
	    	getLogger().error("Exception occured while waiting for element : {}", ex);
	    }
		return element;
	}
	
}
