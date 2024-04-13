package jaywayPathValidator;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DecerlizationTesingOfProductAPI {

	@Test
	public void getProductTest_with_POJO() {

		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = given().log().all().when().log().all().get("/products");

		String jsonResponse = response.getBody().asString();

		ObjectMapper mapper = new ObjectMapper();

		try {
			Product product[] = mapper.readValue(jsonResponse, Product[].class);

			for (Product p : product) {

				System.out.println("Product Id : " + p.getId());
				System.out.println("Title : " + p.getTitle());
				System.out.println(" Price : " + p.getPrice());
				System.out.println(" Description : " + p.getDescription());
				System.out.println(" Category : " + p.getCategory());
				System.out.println(" Image : " + p.getImage());
				System.out.println("Rate :" + p.getRating().getRate());
				System.out.println(" Count : " + p.getRating().getCount());

				System.out.println("--------------------------------------");
			}
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void getProductTest_with_POJO_Lambok() {

		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response = given().log().all().when().log().all().get("/products");

		String jsonResponse = response.getBody().asString();

		ObjectMapper mapper = new ObjectMapper();

		try {
			ProductLombok product[] = mapper.readValue(jsonResponse, ProductLombok[].class);

			for (ProductLombok p : product) {
//
//				System.out.println("Product Id : " + p.getId());
//				System.out.println("Title : " + p.getTitle());
//				System.out.println(" Price : " + p.getPrice());
//				System.out.println(" Description : " + p.getDescription());
//				System.out.println(" Category : " + p.getCategory());
//				System.out.println(" Image : " + p.getImage());
//				System.out.println("Rate :" + p.getRating().getRate());
//				System.out.println(" Count : " + p.getRating().getCount());

				System.out.println("--------------------------------------");
			}
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
	}
}
