package com.androjavatech4u.report;

import com.aventstack.extentreports.ExtentTest;

public class Extent {
	public static ExtentTest extentTest;

	public static ExtentTest getTest() {
		return extentTest;
	}

	public static void setTest(ExtentTest extentTest) {
		Extent.extentTest = extentTest;
	}

}
