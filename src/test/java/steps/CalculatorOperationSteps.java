package steps;

import org.junit.Assert;
import base.BaseUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Steps for windows 10 calculator operation
 */
public class CalculatorOperationSteps extends BaseUtil{
	
	BaseUtil testConext;
	
	public CalculatorOperationSteps(BaseUtil testConext) {
		this.testConext = testConext;
	}
	
	@Given("I am on windows calculator app")
	public void i_open_Windows_Calculator_Application() {
		testConext.setupDesktopDriver();
		testConext.getLogger().info("Calculator app launched");
	}

	@When("I enter number {string}")
	public void i_enter_number(String number) {
		testConext.getCalculaterPage().enterNumber(number);
		testConext.getLogger().info("Number entered in calculator is : {}", number);
	}

	@When("I click on square root button")
	public void i_click_on_square_root_button() {
		testConext.getCalculaterPage().clickSquareRootBtn();
		testConext.getLogger().info("Click action is performed on square root button");
	}

	@Then("the result should be {string}")
	public void the_result_should_be(String expectedResult) {
		//Double expected = Double.parseDouble(expectedResult);
		String actualResult = testConext.getCalculaterPage().getResult();
		testConext.getLogger().info("Square root is {}", actualResult);
		Assert.assertTrue(actualResult.contains(expectedResult));
	}
}
