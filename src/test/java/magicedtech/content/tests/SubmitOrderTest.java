package magicedtech.content.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import magicedtech.content.TestComponents.BaseTest;
import magicedtech.content.pageobjects.Cart;
import magicedtech.content.pageobjects.Checkout;
import magicedtech.content.pageobjects.Order;
import magicedtech.content.pageobjects.Orderhistory;
import magicedtech.content.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	String country = "China";
	String expectedMessage = "Thankyou for the order.";


	@Test (dataProvider="getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.login(input.get("email"),input.get("password"));
		productCatalogue.addProductToCart(input.get("product"));
		Cart cart = productCatalogue.goToCartPage();
		boolean resultOne = cart.isProductAvailable(input.get("product"));
		Assert.assertTrue(resultOne);
		Checkout checkout = cart.clickCheckout();
		checkout.finalCityClick(country);
		Order order = checkout.clickPlaceOrder();
		boolean resultTwo = order.isMessageDispalyed(expectedMessage);
		Assert.assertTrue(resultTwo);
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistory() {
		ProductCatalogue productCatalogue = landingPage.login("vibhor@gmail.com", "Vibhor@123");
		Orderhistory orderhistory = productCatalogue.goToOrderPage();
		boolean resultThree = orderhistory.verifyOrderDisplay(productName);
		Assert.assertTrue(resultThree);
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap("/src/test/java/magicedtech/content/data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	@DataProvider
	public Object[][] getDataHashMap() {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "vibhor@gmail.com");
		map.put("password", "Vibhor@123");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "charu23@gmail.com");
		map1.put("password", "Charu@123");
		map1.put("product", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
	}
	
	@DataProvider
	public Object[][] getDataSimple() {
		
		return new Object[][] {{"vibhor@gmail.com","Vibhor@123","ZARA COAT 3"},{"charu23@gmail.com","Charu@123","ADIDAS ORIGINAL"}};

	}

}
