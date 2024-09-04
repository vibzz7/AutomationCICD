package magicedtech.content.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import magicedtech.content.AbstractComponents.AbstractComponent;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class Orderhistory extends AbstractComponent{
	
	WebDriver driver;
	
	public Orderhistory(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//tr[@class='ng-star-inserted']/td[2]")
	List<WebElement> orderList;
	
	
	public Boolean verifyOrderDisplay(String productName) {
		
		Boolean match = orderList.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
		
	}
	

	
}
