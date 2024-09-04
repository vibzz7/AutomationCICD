package magicedtech.content.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import magicedtech.content.AbstractComponents.AbstractComponent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Order extends AbstractComponent{
	
	WebDriver driver;
	
	public Order(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//h1[@class='hero-primary']")
	WebElement message;
	
	@FindBy(xpath="//ul[@class='cartWrap ng-star-inserted']//h3")
	List<WebElement> cartList;
	
	
	public String getMessage() {
		String msg = message.getText();
		return msg;
	}
	
	public boolean isMessageDispalyed(String expectedMessage) {
		boolean result = getMessage().equalsIgnoreCase(expectedMessage);
		return result;
	}
	

	
}
