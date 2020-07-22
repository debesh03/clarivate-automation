package base;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CalculaterPage;
import pages.GoogleSearchPage;
import pages.SearchResultsPage;

/**
 * Responsible for initializing the page objects and constants.
 *
 */
public class BaseUtil {
	
	public WebDriver driver;
	public WebDriver winiumDriver;
	public Logger log;
	public static final String CALCULATOR_PATH = "C:/Windows/System32/calc.exe";
	public static final String HOST_SERVER = "http://localhost:9999";
	public static final String WINIUM_DRIVER_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\Winium.Desktop.Driver.exe";   
	private GoogleSearchPage googleSearchPage;
	private SearchResultsPage searchResultsPage;
	private CalculaterPage calculaterPage;
	
	public Logger getLogger() {
		return log != null? log : LogManager.getLogger(this.getClass().getCanonicalName());
	}

	/**
	 * Initialize the WebDriver based on browser name
	 * @param browser the browser name
	 * @return the WebDriver instance
	 */
	public WebDriver setupBrowser(String browser){
		getLogger().info("Initializing browser : {}", browser);
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			this.driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else{
			getLogger().error("Browser is not correct");
			throw new RuntimeException("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getLogger().info("Implicit wait applied on webdriver for 10 seconds");
		return driver;
	}
	
	/**
	 * Initialize the Winium driver
	 * @return the WiniumDriver instance
	 */
	public WebDriver setupDesktopDriver() {
		getLogger().info("Initializing winium driver");
		ProcessBuilder builder = new ProcessBuilder(WINIUM_DRIVER_PATH).inheritIO();
		try {
			builder.start();
		} catch (IOException e1) {
			getLogger().fatal("Winium driver initialization failed {}", e1);
			throw new RuntimeException("Error occured while initializing winium driver "+e1);
		}
        DesktopOptions options = new DesktopOptions();
        options.setApplicationPath(CALCULATOR_PATH);
        try {
			this.winiumDriver = new WiniumDriver(new URL(HOST_SERVER),options);
		} catch (MalformedURLException e) {
			getLogger().fatal("Error in url {}", e);
			throw new RuntimeException("Error in url "+ e);
		}
        getLogger().info("Winium driver instantiated");
        return winiumDriver;
    }
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	public WebDriver getWiniumDriver() {
		return this.winiumDriver;
	}
	
	/**
	 * @return Same GoogleSearchPage instance once it's initialized
	 */
	public GoogleSearchPage getGoogleSearchPage() {
		return googleSearchPage != null? googleSearchPage : new GoogleSearchPage(driver);
	}
	
	/**
	 * @return Same SearchResultsPage instance once it's initialized
	 */
	public SearchResultsPage getSearchResultsPage() {
		return searchResultsPage != null? searchResultsPage : new SearchResultsPage(driver);
	}
	
	/**
	 * @return Same CalulatorPage instance once it's initialized
	 */
	public CalculaterPage getCalculaterPage() {
		return calculaterPage != null? calculaterPage : new CalculaterPage(winiumDriver) ;
	}
}
