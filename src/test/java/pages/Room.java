package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Room {
	
	
	public WebDriver driver;
	
	public Room(WebDriver driver) {
		this.driver = driver;
		System.out.println(driver.getTitle());
		
	}
	
	public List<WebElement> btn_blue; 
	public List<WebElement> resultsCount;
	
	
	public By txt_username = By.cssSelector("input#username.form-control");
	
	
	public void changeUsername(String username) {
		WebElement user = driver.findElement(txt_username);
		user.click();
		user.clear();
		user.sendKeys(username);	
	}

	
	public long blueButtonsCount() { 
	
		btn_blue = driver.findElements(By.cssSelector(".btn.btn-primary"));
		WebDriverWait listWait = new WebDriverWait(driver,10);
		listWait.until(ExpectedConditions.visibilityOfAllElements(btn_blue));
		System.out.println("resultsCount ="+ listWait);
		System.out.println("resultsCount ="+ btn_blue.stream().count());
		long result = btn_blue.stream().count();
	
	return result;
	}
	
	
	public void selectDice (String diceType) {
	
		WebDriverWait wait = new WebDriverWait(driver,5); /// Explicit wait - wait a max of 5 seconds (will poll every 500 ms to check if condition is true)
		WebElement dice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(diceType)));
		dice.click();
		
	}
	
	
	public void throwDice () {
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);// alternate method of sleep
		//WebElement throwdice = driver.findElement(By.cssSelector("button.btn.btn-danger"));
		WebElement throwdice = driver.findElement(By.cssSelector(".btn.btn-danger")); // spaces are replaced by .
		throwdice.click();
	 
	}
	
	
	public long resultsCount() { 
		
		resultsCount = driver.findElements(By.cssSelector("[id^='61']")); //regex to find ids that start with 61, careful with single quotes due to number
		WebDriverWait listWait = new WebDriverWait(driver,10);;
		long result = resultsCount.stream().count();
	
	return result;
	}
	
	

	public void closeBrowser() {
		driver.close();
	    driver.quit();
		
	}
	
}
