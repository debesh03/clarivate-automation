package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;

import base.BaseUtil;

/**
 * Perform the pre and post steps for scenario.
 *
 */
public class Hooks extends BaseUtil{
	
	ProcessBuilder builder;
    BaseUtil testConext;
   
    public Hooks(BaseUtil testConext) {
        this.testConext = testConext;
    }
    
    /**
     * Run after the @Webapp Scenario and quit the WebDriver
     */
    @After("@WebApp")
    public void tearDown() {
    	testConext.getDriver().quit();
    	getLogger().info("Webdriver closed");
    }


    /**
     * Run after the @DesktopApp Scenario and destroy the winium and calculator process
     */
    @After("@DesktopApp")
    public void quitWiniumDriver() {
    	// Destroying the Winium.Desktop.Driver.exe execution process
    	testConext.getCalculaterPage().closeCalc();
		testConext.getLogger().info("Calculator closed successfully");
		try {
			Process process = Runtime.getRuntime().exec("taskkill /F /IM Winium.Desktop.Driver.exe");
			process.waitFor();
			process.destroy();
	    	getLogger().info("Winium driver closed");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			getLogger().error("Error occured while closing the Winium driver : {}", e);
		}
    }
}
