package cisco.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import cisco.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		// we will create extent report object here
		
		test = extent.createTest(result.getMethod().getMethodName());
		
//		now we store our test object inside "extentTest.set(test)" for unique thread id using set() method:-
		extentTest.set(test); //unique thread id(test method)->test
	
		
	}
	
	
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "Test passed");
		
		
	}
	
	
	@Override
	public void onTestFailure(ITestResult result) {
		
//		showing error messages
		extentTest.get().fail(result.getThrowable());
		
//		getting the driver life:-
		try {
			driver= (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		In this we will take Screenshots & save to the path:-
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		adding screenshot to the extent report:-
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		
	}
	
	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
		
		
		
	}

	
	
	
	
	
}
