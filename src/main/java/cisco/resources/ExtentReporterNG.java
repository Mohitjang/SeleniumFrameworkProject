package cisco.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
//	Create methhod which will create report object:-

	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"//Reports//index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","Mohit Jangid");
		
		extent.createTest(path);
		
		return extent;
		
	}
	
	
	
}
