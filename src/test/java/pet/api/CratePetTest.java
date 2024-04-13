package pet.api;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pet.api.PetLombak.Category;
import pet.api.PetLombak.Tag;


public class CratePetTest {

	@Test
	public void createPetTest() {
		
		RestAssured.baseURI="https://petstore.swagger.io";
		
		Category category = new Category(1, "Dog");
		List<String> photoUrls = Arrays.asList("https://www.dog.com","https://www.cat.com");
		Tag tag1 = new Tag(5, "red");
		Tag tag2 = new Tag(5, "black");
		List<Tag> tags =Arrays.asList(tag1,tag2);
		
		PetLombak pet = new PetLombak(101,category,"Rocky",photoUrls,tags,"available");
		
		Response response =RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.body(pet)
		.when().log().all()
		.post("/v2/pet");
		
		System.out.println(response.statusCode());
		
		// De-serialize
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			PetLombak petRes = mapper.readValue(response.getBody().asString(), PetLombak.class);
			System.out.println(petRes.getId());
			System.out.println(petRes.getName());
			System.out.println(petRes.getStatus());
			
			System.out.println(petRes.getCategory().getId());
			System.out.println(petRes.getCategory().getName());
			
			System.out.println(petRes.getPhotoUrls());
			
			System.out.println(petRes.getTags().get(1).getId());
			System.out.println(petRes.getTags().get(1).getName());
			
		} catch (JsonMappingException e) {
						e.printStackTrace();
		} catch (JsonProcessingException e) {
					e.printStackTrace();
		}
	}

	@Test
public void createPet_WithBuilderPatternTest() {
		
		RestAssured.baseURI="https://petstore.swagger.io";
		
		Category category = new Category.CategoryBuilder()
				.id(01)
				.name("Animal")
				.build();
		
		Tag tag1 = new Tag.TagBuilder()
				.id(11)
				.name("red")
				.build();
		Tag tag2 = new Tag.TagBuilder()
				.id(11)
				.name("black")
				.build();
	
		List<Tag> tags =Arrays.asList(tag1,tag2);
		
		PetLombak pet = new PetLombak.PetLombakBuilder()
				.id(201)
				.category(category)
				.name("robby")
				.photoUrls(Arrays.asList("https://www.dog.com","https://www.cat.com"))
				.tags(tags)
				.status("available")
				.build();
		
		Response response =RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.body(pet)
		.when().log().all()
		.post("/v2/pet");
		
		System.out.println(response.statusCode());
		
		// De-serialize
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			PetLombak petRes = mapper.readValue(response.getBody().asString(), PetLombak.class);
			System.out.println(petRes.getId());
			System.out.println(petRes.getName());
			System.out.println(petRes.getStatus());
			
			System.out.println(petRes.getCategory().getId());
			System.out.println(petRes.getCategory().getName());
			
			System.out.println(petRes.getPhotoUrls());
			
			System.out.println(petRes.getTags().get(1).getId());
			System.out.println(petRes.getTags().get(1).getName());
			
		} catch (JsonMappingException e) {
						e.printStackTrace();
		} catch (JsonProcessingException e) {
					e.printStackTrace();
		}
	}
}
