package com.androjavatech4u.testcases;

import static org.hamcrest.Matchers.*;
//import static io.restassured.Restassured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.androjavatech4u.base.RestAssuredBase;
import com.androjavatech4u.utils.PropertyReader;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class UserTestCase extends RestAssuredBase {
	public static Object baseURI = null;

	@Test(priority = 1)
	public void CreateUser() {

		String userUrl = PropertyReader.getConfigProperty("createuser");
		String userPayload = PropertyReader.getDataProperty("userPayload");

		if (userUrl != null && userPayload != null) {
			ValidatableResponse validatableResponse = RestAssuredBase.postCall(userUrl, userPayload);
			validatableResponse.assertThat().log().all().extract().response();
			validatableResponse.statusCode(201);

			validatableResponse.body("name", equalTo("Deeksha"));
			validatableResponse.body("job", equalTo("Tester"));
		} else {

		}
	}

	@Test(priority = 2)
	public void registerUnSuccessfully() {
		String userUrl = PropertyReader.getConfigProperty("registeruser");
		String userPayload = PropertyReader.getDataProperty("registerPayload");

		if (userUrl != null && userPayload != null) {
			ValidatableResponse validatableResponse = RestAssuredBase.postCall(userUrl, userPayload);
			validatableResponse.assertThat().log().all().extract().response();
			validatableResponse.statusCode(400);
			validatableResponse.body("error", equalTo("Missing password"));
		} else {

		}
	}

	@Test(priority = 3)
	public void registerSuccessfull() {
		String userUrl = PropertyReader.getConfigProperty("registerSuccess");
		String userPayload = PropertyReader.getDataProperty("registerSuccessPayload");

		if (userUrl != null && userPayload != null) {
			ValidatableResponse validatableResponse = RestAssuredBase.postCall(userUrl, userPayload);
			validatableResponse.assertThat().log().all().extract().response();
			validatableResponse.statusCode(200);
			validatableResponse.body("id", equalTo(4));
			validatableResponse.body("token", equalTo("QpwL5tke4Pnpja7X4"));

		} else {

		}
	}

	@Test(priority = 4)
	public void loginSuccessfull() {
		String userUrl = PropertyReader.getConfigProperty("loginSuccess");
		String userPayload = PropertyReader.getDataProperty("loginSuccessPayload");

		if (userUrl != null && userPayload != null) {
			ValidatableResponse validatableResponse = RestAssuredBase.postCall(userUrl, userPayload);
			validatableResponse.assertThat().log().all().extract().response();
			validatableResponse.statusCode(200);
			validatableResponse.body("token", equalTo("QpwL5tke4Pnpja7X4"));
		} else {

		}
	}

	@Test(priority = 5)
	public void loginUnSuccessfull() {
		String userUrl = PropertyReader.getConfigProperty("loginUnSuccess");
		String userPayload = PropertyReader.getDataProperty("loginUnSuccessPayload");

		if (userUrl != null && userPayload != null) {
			ValidatableResponse validatableResponse = RestAssuredBase.postCall(userUrl, userPayload);
			validatableResponse.assertThat().log().all().extract().response();
			validatableResponse.statusCode(400);
			validatableResponse.body("error", equalTo("Missing password"));
		} else {

		}
	}

	@Test(priority = 6)
	public void usersList()

	{
		String userUrl = PropertyReader.getConfigProperty("UserList");

		ValidatableResponse validatableResponse = RestAssuredBase.getcall(userUrl);
		validatableResponse.extract().response();

		validatableResponse.statusCode(200);
		validatableResponse.body("page", equalTo(2));
		validatableResponse.body("per_page", equalTo(6));
		validatableResponse.body("total", equalTo(12));
		validatableResponse.body("total_pages", equalTo(2));

	}

	@Test(priority = 7)
	public void singleUser() {
		String userUrl = PropertyReader.getConfigProperty("SingleUser");

		ValidatableResponse validatableResponse = RestAssuredBase.getcall(userUrl);
		validatableResponse.extract().response();

		validatableResponse.statusCode(200);
		validatableResponse.body("data.id", equalTo(2));
		validatableResponse.body("data.first_name", equalTo("Janet"));
		validatableResponse.body("data.last_name", equalTo("Weaver"));
		validatableResponse.body("data.email", equalTo("janet.weaver@reqres.in"));
	}

	@Test(priority = 8)
	public void singleUserNotFound() {
		String userUrl = PropertyReader.getConfigProperty("SingleUserNotFound");

		ValidatableResponse validatableResponse = RestAssuredBase.getcall(userUrl);
		validatableResponse.extract().response();

		validatableResponse.statusCode(404);
	}

	@Test(priority = 9)
	public void listResource() {
		String userUrl = PropertyReader.getConfigProperty("ListResource");

		ValidatableResponse validatableResponse = RestAssuredBase.getcall(userUrl);
		validatableResponse.extract().response();

		validatableResponse.statusCode(200);
		validatableResponse.body("page", equalTo(1));
		validatableResponse.body("per_page", equalTo(6));
		validatableResponse.body("total", equalTo(12));
		validatableResponse.body("total_pages", equalTo(2));
		validatableResponse.body("data[1].name", equalTo("fuchsia rose"));
		validatableResponse.body("data[4].year", equalTo(2004));
		validatableResponse.body("data[5].color", equalTo("#53B0AE"));
	}

	@Test(priority = 10)
	public void singleResource() {
		String userUrl = PropertyReader.getConfigProperty("SingleResource");

		ValidatableResponse validatableResponse = RestAssuredBase.getcall(userUrl);
		validatableResponse.extract().response();

		validatableResponse.statusCode(200);
		validatableResponse.body("data.id", equalTo(2));
		validatableResponse.body("data.name", equalTo("fuchsia rose"));
		validatableResponse.body("data.year", equalTo(2001));
		validatableResponse.body("data.color", equalTo("#C74375"));
		validatableResponse.body("data.pantone_value", equalTo("17-2031"));

	}

	@Test(priority = 11)
	public void singleResourceNotFound() {
		String userUrl = PropertyReader.getConfigProperty("SingleResourceNotFound");

		ValidatableResponse validatableResponse = RestAssuredBase.getcall(userUrl);
		validatableResponse.extract().response();

		validatableResponse.statusCode(404);
	}

	@Test(priority = 12)
	public void delayedResponse() {
		String userUrl = PropertyReader.getConfigProperty("DelayedResponse");

		ValidatableResponse validatableResponse = RestAssuredBase.getcall(userUrl);
		validatableResponse.extract().response();

		validatableResponse.statusCode(200);
		validatableResponse.body("page", equalTo(1));
		validatableResponse.body("per_page", equalTo(6));
		validatableResponse.body("total", equalTo(12));
		validatableResponse.body("total_pages", equalTo(2));
		validatableResponse.body("data[0].email", equalTo("george.bluth@reqres.in"));
		validatableResponse.body("data[2].first_name", equalTo("Emma"));
		validatableResponse.body("data[4].last_name", equalTo("Morris"));
		validatableResponse.body("data[5].id", equalTo(6));

	}

	@Test(priority = 13)
	public void updateUser() {
		String userUrl = PropertyReader.getConfigProperty("UpdateUserInfo");
		String userPayload = PropertyReader.getDataProperty("UpdateUserInfoPayload");

		if (userUrl != null && userPayload != null) {
			ValidatableResponse validatableResponse = RestAssuredBase.putCall(userUrl, userPayload);
			validatableResponse.assertThat().log().all().extract().response();
			validatableResponse.body("name", equalTo("morpheus"));
			validatableResponse.body("job", equalTo("zion resident"));
		} else {

		}
	}

	@Test(priority = 14)
	public void UserUpdatePatchApi() {
		String userUrl = PropertyReader.getConfigProperty("UserUpdate2Info");
		String userPayload = PropertyReader.getDataProperty("UserUpdate2InfoPayload");

		if (userUrl != null && userPayload != null) {
			ValidatableResponse validatableResponse = RestAssuredBase.patchCall(userUrl, userPayload);
			validatableResponse.assertThat().log().all().extract().response();
			validatableResponse.body("name", equalTo("morpheus"));
			validatableResponse.body("job", equalTo("zion resident"));
		} else {

		}

	}

	@Test(priority = 15)
	public void deleteUser() {
		String userUrl = PropertyReader.getConfigProperty("DeleteUser");

		ValidatableResponse validatableResponse = RestAssuredBase.deletecall(userUrl);
		validatableResponse.extract().response();
	}
}