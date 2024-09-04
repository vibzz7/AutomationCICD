package magicedtech.content.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productName = "IPHONE 13 PRO";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("vibhor@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Vibhor@123");
		driver.findElement(By.id("login")).click();
		
		Thread.sleep(3000);

		List<WebElement> products = driver.findElements(By.xpath("(//div[contains(@class,'mb-3')])"));
		WebElement prod = products.stream()
				.filter(s -> s.findElement(By.xpath(".//b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.xpath(".//button[2]")).click();
		
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));	
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();
		
		
		List<WebElement> cartProducts = driver.findElements(By.xpath("//ul[@class='cartWrap ng-star-inserted']//h3"));
		boolean result = cartProducts.stream().anyMatch(s->s.getText().equals(productName));
		
		Assert.assertTrue(result);
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("chi");
		List<WebElement> city = driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
		WebElement ct = city.stream()
				.filter(s -> s.findElement(By.xpath(".//span")).getText().contains("China")).findFirst()
				.orElse(null);
		ct.findElement(By.xpath(".//span")).click();
		
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		
		Thread.sleep(3000);
		
		List<WebElement> checkoutProducts = driver.findElements(By.xpath("//div[@class='title']"));
		boolean resultTwo = checkoutProducts.stream().anyMatch(s->s.getText().equals(productName));
		
		Assert.assertTrue(resultTwo);
		
		String messsage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		System.out.println(messsage);
		boolean resultThree = messsage.equalsIgnoreCase("Thankyou for the order.");
		
		Assert.assertTrue(resultThree);

	}

}
