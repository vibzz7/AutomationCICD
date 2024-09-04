package magicedtech.content.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporternNG {
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Magic Automation Results");
		reporter.config().setDocumentTitle("MagicedTech");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("tester", "Vibhor Tripathi");
		
		return extent;
		
	}

}

