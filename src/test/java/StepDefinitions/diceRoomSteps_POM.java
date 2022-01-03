package StepDefinitions;

import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*; // * us faster
import pages.Room;


public class diceRoomSteps_POM {

	WebDriver driver = null;
	Room room;

	List<WebElement> resultsCount;
	long initialResultsCount = 0;
	long expectedUpdatedResults = 0; 

	@Given("I open a room page")
	public void i_open_a_room_page() {
		System.out.println("InsideStep - BroswerOpen");

		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is :" +projectPath);

		System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/drivers/chromedriver.exe"); // should use relative path

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@And("the page loads")
	public void the_page_loads() throws InterruptedException {
		System.out.println("InsideStep - Page Loads");
		driver.navigate().to("https://throwdice.net/rooms/anzo");
		room = new Room(driver); // new instance of Room Class 

		Thread.sleep(500);
		initialResultsCount = room.resultsCount();
		expectedUpdatedResults = initialResultsCount+1; 
		System.out.println("--- Initial ResultsCount = " +initialResultsCount + " --- ExpectedResultsCount = " +expectedUpdatedResults) ;

	}

	@Then("all blue buttons appear")
	public void buttons_appear() throws InterruptedException {

		long buttonCount = room.blueButtonsCount();
		assertEquals(7, buttonCount);
	}

	@Then("a text box is available for me to enter my username")
	public void a_text_box_is_available_for_me_to_enter_my_username() throws InterruptedException {
		Thread.sleep(100);
		driver.findElement(room.txt_username);
		//driver.findElement(By.cssSelector("input#username.form-control"));
	}

	@When("I change my username")
	public void i_change_my_username() {
		room.changeUsername("test");
	}

	@And("select and roll a D20")
	public void select_and_roll_a_d20() throws InterruptedException {

		room.selectDice("20");
		room.selectDice("100");

		room.throwDice();
	}

	@Then("the dice result should appear under my new username")
	public void the_dice_result_should_appear_under_my_new_username() throws InterruptedException {

		Thread.sleep(2000); //careful with sleeps, as the actual result can trigger before the expected result
		long updatedResults = room.resultsCount();
		assertEquals(expectedUpdatedResults, updatedResults);
	}


	@Then("the brower should close")
	public void the_browser_should_close() throws InterruptedException{
		Thread.sleep(3000); //careful with sleeps, as the actual result can trigger before the expected result
		room.closeBrowser();
	}






}
