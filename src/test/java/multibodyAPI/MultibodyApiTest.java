package multibodyAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class MultibodyApiTest {

	
	@Test
	public void bodyWithTextTest() {
		RestAssured.baseURI="https://httpbin.org";
		String payload ="Hi this is yogesh";
		
		RestAssured.given()
		.contentType(ContentType.TEXT)
		.body(payload)
		.when()
		.post("/post")
		.then()
		.assertThat()
		.statusCode(200);	
	}
	
	@Test
	public void bodyWithJavaScriptTest() {
		
		RestAssured.baseURI="https://httpbin.org";
			Response response=RestAssured.given()
		.contentType(ContentType.TEXT)
		.body("function login() {\r\n"
				+ "    let x = 10\r\n"
				+ "    let y = 20\r\n"
				+ "    console.log(x + y)\r\n"
				+ "}")
		.when()
		.post("/post");
		
		response.prettyPrint();
		System.out.println(response.statusCode());	
		}

	@Test
public void bodyWithHTMLTest() {
	
	RestAssured.baseURI="https://httpbin.org";
		Response response=RestAssured.given()
	.contentType(ContentType.XML)
	.body("<!DOCTYPE html>\r\n"
			+ "<html>\r\n"
			+ "<body>\r\n"
			+ "\r\n"
			+ "<h1>My First Heading</h1>\r\n"
			+ "\r\n"
			+ "<p>My first paragraph.</p>\r\n"
			+ "\r\n"
			+ "</body>\r\n"
			+ "</html>")
	.when()
	.post("/post");
	
	response.prettyPrint();
	System.out.println(response.statusCode());	
	}
	
	@Test
	public void bodyWithXMLTest() {
		
		RestAssured.baseURI="https://httpbin.org";
			Response response=RestAssured.given()
		.contentType(ContentType.HTML)
		.body("<note>\r\n"
				+ "<to>Tove</to>\r\n"
				+ "<from>Jani</from>\r\n"
				+ "<heading>Reminder</heading>\r\n"
				+ "<body>Don't forget me this weekend!</body>\r\n"
				+ "</note>")
		.when()
		.post("/post");
		
		response.prettyPrint();
		System.out.println(response.statusCode());	
		}
	
	@Test
	public void bodyWithMultiFormDataTest() {
		
		RestAssured.baseURI="https://httpbin.org";
			Response response=RestAssured.given()
		.contentType(ContentType.MULTIPART)
		.multiPart("name","testing")
		// The file path is wrong
		.multiPart("fileName","C:\\Users\\MANGESH PC\\Desktop\\Assesment.txt")
		.when()
		.post("/post");
		
		response.prettyPrint();
		System.out.println(response.statusCode());	
		}
}
