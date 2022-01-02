package StepDefinitions;


import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*; // * us faster


public class diceRoomSteps {

	WebDriver driver = null;
	List<WebElement> resultsCount;
	long initialResultsCount =0;
	long expectedUpdatedResults = 0; 
	

@Given("I open a room page")
public void i_open_a_room_page() {
	System.out.println("InsideStep - BroswerOpen");
	String projectPath = System.getProperty("user.dir");
	System.out.println("Project path is :" +projectPath);
	System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/drivers/chromedriver.exe"); // should use relative path
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
}

@When("the page loads")
public void the_page_loads() throws InterruptedException {
	System.out.println("InsideStep - Page Loads");
	driver.navigate().to("https://throwdice.net/rooms/anzo");
	Thread.sleep(1000);
	resultsCount = driver.findElements(By.cssSelector("[id^='61']")); //regex to find ids that start with 61, careful with single quotes due to number
	initialResultsCount = resultsCount.stream().count();
	System.out.println("--- Initial ResultsCount = " +initialResultsCount) ;
	expectedUpdatedResults = initialResultsCount+1; 
	System.out.println("--- ExpectedResultsCount = " +expectedUpdatedResults) ;
}

@Then("all blue buttons appear")
public void buttons_appear() throws InterruptedException {
//	resultsCount = driver.findElements(By.cssSelector(".btn.btn-primary"));
//	Thread.sleep(1000);
//	System.out.println("resultsCount ="+ resultsCount);
	
	 List<WebElement> blueButtons = new ArrayList<WebElement>();
	blueButtons = driver.findElements(By.cssSelector(".btn.btn-primary"));

	WebDriverWait listWait = new WebDriverWait(driver,10);
	listWait.until(ExpectedConditions.visibilityOfAllElements(blueButtons));
	System.out.println("resultsCount ="+ listWait);
	System.out.println("resultsCount ="+ blueButtons.stream().count());
	long result = blueButtons.stream().count();
	System.out.println("long resultsCount = "+ result);
	
	assertEquals(7, result);
	
	
}

@Then("a text box is available for me to enter my username")
public void a_text_box_is_available_for_me_to_enter_my_username() throws InterruptedException {
	Thread.sleep(100);
	driver.findElement(By.cssSelector("input#username.form-control"));

}

@When("I change my username")
public void i_change_my_username() {
	WebElement changename = driver.findElement(By.cssSelector("input#username.form-control"));
	changename.click();
	changename.clear();
	changename.sendKeys("anzotest");
}

@And("select and roll a D20")
public void select_and_roll_a_d20() throws InterruptedException {
	WebElement d20 = driver.findElement(By.id("20"));
	d20.click();

	WebDriverWait wait = new WebDriverWait(driver,5); /// Explicit wait - wait a max of 5 seconds (will poll every 500 ms to check if condition is true)
	WebElement autocompleteResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("4")));
	autocompleteResult.click();

	//driver.findElement(By.id("100")).click();

	Thread.sleep(100);
	driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);// alternate method of sleep
	//WebElement throwdice = driver.findElement(By.cssSelector("button.btn.btn-danger"));
	WebElement throwdice = driver.findElement(By.cssSelector(".btn.btn-danger")); // spaces are replaced by .
	/// does not work WebElement throwdice = driver.findElement(By.cssSelector("button.btn.btn-danger[value='Throw!']"));
	//does not work, copy xpath
	//WebElement throwdice = driver.findElement(By.xpath("/div/div/div[3]/button[9]"));
	throwdice.click();
 
	

}

@Then("the dice result should appear under my new username")
public void the_dice_result_should_appear_under_my_new_username() throws InterruptedException {

	Thread.sleep(2000); //careful with sleeps, as the actual result can trigger before the expected result
	List<WebElement> newResultsCount = driver.findElements(By.cssSelector("[id^='61']")); //regex to find ids that start with 61, careful with single quotes due to number
	System.out.println("--- updatedResultsCount ="+ newResultsCount.stream().count());
	long updatedResultsCount = newResultsCount.stream().count();
	Thread.sleep(500);
	System.out.println(expectedUpdatedResults + " : -- : " + updatedResultsCount);
	assertEquals(expectedUpdatedResults, updatedResultsCount);
	
	
	
	driver.close();
    driver.quit();
}








}
