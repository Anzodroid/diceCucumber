package StepDefinitions;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

// junit needed to remove errors 
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features", // forwardslash better as it works on linux mac and win
glue= {"StepDefinitions"},
monochrome = true,
plugin = {"pretty", "html:target/Html/HtmlReports",
		"json:target/JsonReports/report.json" ,
		"junit:target/JunitReports/report.xml"}
//,
//tags="@SmokeTest"
		)



public class TestRunner {

}
