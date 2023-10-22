package cisco.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cisco.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement country = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
//	driver.findElement(By.cssSelector(".action__submit"))
	@FindBy(css=".action__submit")
	WebElement submit;
	
//	driver.findElement(By.cssSelector(".ta-item"));
	@FindBy(css="button[class*='ta-item']:nth-child(3)")
	WebElement selectCountry;
	
	By results = By.cssSelector("button[class*='ta-item']:nth-child(3)");
	
	public void selectCountry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-item"))));
		waitForElementToAppear(results);
//		driver.findElement(By.cssSelector(".ta-item")).click();
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
//		driver.findElement(By.cssSelector(".action__submit")).click();
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	
}
