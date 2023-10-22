package cisco.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cisco.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

//	Login page object:

//	1. Local class variable:-
	WebDriver driver;

//	2. first this constructor will execute that's why we will use this to initialize driver:-
	public LandingPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
//	3. 	this trigger initialize elements in the class with the help of driver and using this keyword it uses local driver:-
		PageFactory.initElements(driver, this);
	}

//	Old type:-
//	WebElement userEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));

//	Latest type:-
//	4. Page factory design pattern:-
	@FindBy(id = "userEmail") // locator
	WebElement userEmail; // variable

//	driver.findElement(By.id("userPassword"))
	@FindBy(id = "userPassword")
	WebElement passwordEle;

//	driver.findElement(By.id("login"))
	@FindBy(id = "login")
	WebElement submit;

//	Wrong credential popup
//	.ng-tns-c4-37.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String password) {

		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}

	public void goTo() {

		driver.get("https://www.rahulshettyacademy.com/client");

	}

	public String getErrorMessage() {

		waitForWebElementToAppear(errorMessage);
		String errMessage = errorMessage.getText();
		return errMessage;

	}


}
