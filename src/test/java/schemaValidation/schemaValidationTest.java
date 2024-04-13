package schemaValidation;

import org.testng.annotations.Test;
import com.put.api.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
public class schemaValidationTest {
	public class CreateUserTestWithLombok {

		public String getRandomEmailId() {
			return "apiAutomation" + System.currentTimeMillis() + "@gmail.com";
		}

		@Test
		public void addUserSchemaTest() {
			RestAssured.baseURI = "https://gorest.co.in";

			User user = new User("yogesh", getRandomEmailId(), "male", "active");
			RestAssured.given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
			.body(user).when().log().all()
			.when()
			.post("/public/v2/users")
			.then()
			.assertThat()
			.statusCode(201)
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("adduserschemavalidator.json"));

		}
		@Test
		public void getUserSchemaTest() {
			RestAssured.baseURI = "https://gorest.co.in";

			RestAssured.given().log().all()
			.header("Authorization", "Bearer fe06a0006eb28608aea0dd91b3d4087397505e0b8d70d03abf0891b799d79674")
			.when().log().all()
			.get("/public/v2/users")
			.then().log().all()
			.assertThat()
			.statusCode(200)
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getuserschema.json"));
		}

	}
}