package utility;

import java.io.IOException;
import java.lang.reflect.Method;

import io.restassured.http.ContentType;
import org.testng.annotations.*;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public abstract class BaseTest extends FrameworkUtility {
	
	protected static RequestSpecification requestSpec;
	protected static ResponseSpecification responseSpec;

	@BeforeSuite
	public void setBaseURI() {
		AllureLogger.logToAllure("The base URI is : " + readConfigurationFile("Base_URI"));
		requestSpec = new RequestSpecBuilder()
				.setBaseUri("http://77.50.236.203:4880/")
				.setContentType(ContentType.JSON)
				.build();
	}

	/*****************************************************************************************************************/
//	@AfterSuite
	public void afterSuite() {

	}
	
	/****************************************************************************************************************/
//	@BeforeClass
	public void beforeClass() {

	}
	
	/****************************************************************************************************************/	
//	@AfterClass
	public void afterClass(){

	}
	
	/************************************************************************************************************************/
	@BeforeMethod
	public void beforeMethod() {
    	responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
	}


//	@AfterMethod
	public void afterMethod() {

	}

}
/*****************************************************************************************************************/