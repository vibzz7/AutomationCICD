package magicedtech.content.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import magicedtech.content.pageobjects.Cart;
import magicedtech.content.pageobjects.Orderhistory;

public class AbstractComponent{
	
	WebDriver driver;
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[3]")
	WebElement cartButton;
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[2]")
	WebElement orderButton;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void waitForElementToAppear(By findBy){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(WebElement ele){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public Cart goToCartPage()
	{
		cartButton.click();
		Cart cart = new Cart(driver);
		return cart;
	}
	
	public Orderhistory goToOrderPage()
	{
		orderButton.click();
		Orderhistory orderhistory = new Orderhistory(driver);
		return orderhistory;
	}
}
