package magicedtech.content.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import magicedtech.content.pageobjects.Landingpage;

public class BaseTest {
	
	public WebDriver driver;
	public Landingpage landingPage;
	
	
	public WebDriver initializeDriver() throws IOException {
		
		 Properties prop = new Properties();
		 FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/magicedtech/content/resources/GlobalData.properties");
		 prop.load(fis);
		String browserName =  System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
		//prop.getProperty("browser");
		System.out.println(browserName);
		
		if (browserName.contains("chrome")){
			 
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1400, 900));
		}
		else if (browserName.equals("firefox")) {
			
		}
		else if (browserName.equals("edge")) {
			 driver = new EdgeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public Landingpage launchApplication() throws IOException {
		
		driver = initializeDriver();
		landingPage = new Landingpage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {

		//read JSON to String
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filePath),StandardCharsets.UTF_8);

		//convert to HashMap 
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		return data;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
	}

}
