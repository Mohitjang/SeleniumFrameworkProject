package cisco.tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String testProduct = "iphone 13 pro";
//		1. Login
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("mjangid@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Mj.12345678");
		driver.findElement(By.id("login")).click();

//		2. add to cart item name iphone 13 pro :-
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//		check for which products is iphone and add to cart:-
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(testProduct))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".mb-3 .card-body button:last-of-type")).click();

//		3. wait for confirmation toast message of add to cart:-
		WebElement toast = driver.findElement(By.cssSelector("#toast-container"));

		wait.until(ExpectedConditions.visibilityOf(toast));
//		4. loading icon :- ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		System.out.println(toast.getText());

//		5. now we have to click on cart button :-
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

//		6. make a list of cart products :-
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".infoWrap h3"));

//		7. Now we have to validate :-
		boolean flag = cartProducts.stream().anyMatch(s -> s.getText().equalsIgnoreCase(testProduct));
		System.out.println(flag);
		assertTrue(flag);

//		8. go to check out page:-
		driver.findElement(By.cssSelector(".subtotal.cf.ng-star-inserted button")).click();

//		9. Select country:-
		WebElement country = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
		Actions a = new Actions(driver);
		a.sendKeys(country, "India").build().perform();

//		10. wait until dropdown item is visible:-
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-item"))));
		driver.findElement(By.cssSelector(".ta-item")).click();

//		11.submit the order.
		driver.findElement(By.cssSelector(".action__submit")).click();

//		Assert the confirmation:-
		String confirmMsg = driver.findElement(By.cssSelector(".box h1")).getText();
//		System.out.println(driver.findElement(By.cssSelector(".box h1")).getText());
		assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));

		System.out.println("code successful.");

		driver.quit();

	}

}
