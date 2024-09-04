package magicedtech.content.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import magicedtech.content.TestComponents.BaseTest;
import magicedtech.content.pageobjects.Cart;
import magicedtech.content.pageobjects.ProductCatalogue;
import magicedtech.content.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest {


	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		landingPage.login("anshika@gmail.com", "Iamki000");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test 
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = landingPage.login("vibhor@gmail.com", "Vibhor@123");
		productCatalogue.addProductToCart(productName);
		Cart cart = productCatalogue.goToCartPage();
		boolean resultOne = cart.isProductAvailable(productName);
		Assert.assertFalse(resultOne);
	}

}
