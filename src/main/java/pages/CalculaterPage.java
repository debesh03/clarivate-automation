package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.BasePage;

/**
 * Locate elements and perform actions on Windows 10 Calculator app
 */
public class CalculaterPage extends BasePage {

	
	/**
	 * Initialize the calculator elements
	 * @param driver the winium driver instance
	 */
	public CalculaterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "squareRootButton")
	WebElement squareRootBtn;

	@FindBy(id = "equalButton")
	WebElement equalBtn;

	@FindBy(id = "CalculatorResults")
	WebElement result;

	@FindBy(name = "Close")
	WebElement close;
	
	
	/**
	 * Perform click on each char in given number
	 * @param number the input number
	 */
	public void enterNumber(String number) {
		getLogger().info("Entering number in calculator : {}", number);
		char[] numbers = String.valueOf(number).toCharArray();
		for (char ch : numbers) {
			String str = "num" + ch + "Button";
			winiumDriver.findElement(By.id(str)).click();
		}
	}

	
	/**
	 * Perform click on the Square root button
	 */
	public void clickSquareRootBtn() {
		squareRootBtn.click();
	}

	
	/**
	 * @return return the end result by clicking equals button
	 */
	public String getResult() {
		equalBtn.click();
		getLogger().info("Click action performed on equal button");
		String displayedResult = result.getAttribute("Name");
		getLogger().info("Calculated result is {}", displayedResult);
		//double parsedResult = Double.parseDouble(displayedResult.split("Display is ")[1]);
		//getLogger().info("Calculated parsed result is {}", parsedResult);
		return displayedResult;
	}

	public void closeCalc() {
		getLogger().info("Closing calculator");
		close.click();
	}
}
