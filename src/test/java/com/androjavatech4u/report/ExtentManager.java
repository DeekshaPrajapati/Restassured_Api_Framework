package com.androjavatech4u.report;

import java.io.PrintStream;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentManager {

	static public ExtentSparkReporter htmlReporter;
	static public ExtentReports reports;
	static public ExtentTest test;

	private ExtentManager() {

	}

	public synchronized static ExtentReports getExtentReportsInstance() {
		if (reports == null) {
			htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\ReqresApi.html");
			htmlReporter.config().setReportName("RestAssured"); // yeh 17 line main store hoga
			htmlReporter.config().setDocumentTitle("Reqres Api Test Report ");
			htmlReporter.config().setTheme(Theme.DARK);
			reports = new ExtentReports();
			reports.setSystemInfo("Operating System", "Windows 11");
			reports.setSystemInfo("Tested By", "Deeksha Prajapati");

			reports.attachReporter(htmlReporter);
			return reports;

		} else {
			return reports;
		}
	}

	public static ExtentReports getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Object getReporter() {
		// TODO Auto-generated method stub
		return null;
	}

}
