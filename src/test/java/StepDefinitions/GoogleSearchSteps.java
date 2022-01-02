package StepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*; // * us faster


public class GoogleSearchSteps {
	
	WebDriver driver = null;
	
	

@Given("browser is open")
public void browser_is_open() {
	System.out.println("InsideStep - BroswerOpen");
	
	String projectPath = System.getProperty("user.dir");
	System.out.println("Project path is :" +projectPath);
	
	System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/drivers/chromedriver.exe"); // should use relative path
	//System.setProperty("webdriver.chrome.driver", "D:/Java/cucumberSelenium/src/test/resources/drivers/chromedriver.exe"); // should use relative path
	
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
}

@And("user is on google search page")
public void user_is_on_google_search_page() {
	System.out.println("InsideStep - Searchpage");
	driver.navigate().to("https://google.com");
}


@When("user enters a test in search box")
public void user_enters_a_test_in_search_box() {
	System.out.println("InsideStep - text entered");
	driver.findElement(By.name("q")).sendKeys("Automation Step by Step");

}

@And("hits enter")
public void hits_enter() throws InterruptedException {
	System.out.println("InsideStep - hits enter");
	driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	Thread.sleep(2000);
}

@Then("user is navigated to search results")
public void user_is_navigated_to_search_results() {
	System.out.println("InsideStep - search results");
	driver.getPageSource().contains("Online Courses");
	
	driver.close();
	driver.quit();
}
	

}
