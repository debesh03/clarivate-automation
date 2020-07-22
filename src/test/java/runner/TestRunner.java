package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Controls the execution of Cucumber tests
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features"},
        		 glue = {"steps"},
        		 dryRun = false,
        		 strict = true,
        		 monochrome = true,
        		 plugin = {"pretty","html:target/cucumber-reports/cucumber-pretty"},
        		 stepNotifications = true)
public class TestRunner {

}
