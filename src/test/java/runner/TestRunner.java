package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/sample.feature", // Location of feature files
    glue = { "stepdefinitions" }, // Package where step definitions are located
    plugin = { "pretty", "html:target/cucumber-reports" }, // Plugins for generating reports
    		tags = "@smoke or @smoke2"     , // Tags to specify which scenarios to run
    dryRun = true // Set to true to only check step definitions without execution
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
