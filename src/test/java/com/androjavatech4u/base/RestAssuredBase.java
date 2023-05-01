package com.androjavatech4u.base;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpStatus;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.androjavatech4u.report.Extent;
import com.androjavatech4u.report.ExtentManager;
import com.androjavatech4u.utils.PropertyReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RestAssuredBase {

	public static String jsonPayload1;
	public static String endPointURL1;
	public static ValidatableResponse validatableResponse = null;

	static String baseUrl = PropertyReader.getConfigProperty("baseUrl");

	static public ValidatableResponse postCall(String endPointURL, String jsonPayload) {
		jsonPayload1 = jsonPayload;
		endPointURL1 = endPointURL;

		RestAssured.baseURI = baseUrl;
		validatableResponse = RestAssured.given().when().header("Content-Type", "application/json").body(jsonPayload)
				.post(endPointURL).then().assertThat();

//.statusCode(400);
		return validatableResponse;
	}

	static public ValidatableResponse getcall(String endPointURL) {
		endPointURL1 = endPointURL;
		RestAssured.baseURI = baseUrl;
		validatableResponse = RestAssured.given().when().header("Content-Type", "application/json").get(endPointURL)
				.then().
				// assertThat().
				// statusCode(200)
				log().all();
		return validatableResponse;

	}

	static public ValidatableResponse putCall(String endPointURL, String jsonPayload) {
		jsonPayload1 = jsonPayload;
		endPointURL1 = endPointURL;

		RestAssured.baseURI = baseUrl;
		validatableResponse = RestAssured.given().when().header("Content-Type", "application/json").body(jsonPayload)
				.put(endPointURL).then().assertThat().statusCode(200);
		return validatableResponse;
	}

	static public ValidatableResponse patchCall(String endPointURL, String jsonPayload) {
		jsonPayload1 = jsonPayload;
		endPointURL1 = endPointURL;

		RestAssured.baseURI = baseUrl;
		validatableResponse = RestAssured.given().when().header("Content-Type", "application/json").body(jsonPayload)
				.patch(endPointURL).then().assertThat().statusCode(200);
		return validatableResponse;
	}

	static public ValidatableResponse deletecall(String endPointURL) {
		endPointURL1 = endPointURL;
		RestAssured.baseURI = baseUrl;
		validatableResponse = RestAssured.given().when().header("Content-Type", "application/json").delete(endPointURL)
				.then().assertThat().statusCode(204).log().all();
		return validatableResponse;

	}

	@BeforeMethod
	public void setUpReport(Method method) {

		ExtentReports reports = ExtentManager.getExtentReportsInstance();
		ExtentTest extentTest = reports.createTest(method.getName());
		Extent.setTest(extentTest);
	}

	@AfterMethod
	protected void afterMethod(ITestResult result, Method method) {
		// this.result=result;

		if (result.getStatus() == ITestResult.FAILURE) {
			try {

				// String screenshotPath = RestAssuredBase.getScreenhot(result.getName());
				// To add it in the extent report

				Extent.getTest().log(Status.FAIL, "Test Case Failed is " + result.getName());
				Extent.getTest()
						.fail("Captured Screenshot is below:" + Extent.getTest().addScreenCaptureFromPath(baseUrl));
				Extent.getTest().log(Status.FAIL, result.getStatus() + "-> Status Code Is");

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		else if (result.getStatus() == ITestResult.SKIP) {
			Extent.getTest().log(Status.SKIP, "Test Skipped " + result.getThrowable());
			Extent.getTest().log(Status.SKIP, "Test Case Skipped is " + result.getName());

		} else {
			Extent.getTest().log(Status.PASS, result.getName() + " ->Test Case Passed ");
			Extent.getTest().log(Status.PASS, result.getClass() + "-> Class Is");
			Extent.getTest().log(Status.PASS, "baseURI Is : " + baseUrl);
			Extent.getTest().log(Status.PASS, "endPointURL Is : " + endPointURL1);
			Extent.getTest().log(Status.PASS, "jsonPayload Is : " + jsonPayload1);
			Extent.getTest().log(Status.PASS, "Status Code = " + 200);

		}
	}

	@AfterSuite
	public void setUpFlushed() {
		ExtentManager.getExtentReportsInstance().flush();

	}

}
