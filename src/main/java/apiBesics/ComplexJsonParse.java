package apiBesics;

import io.restassured.path.json.JsonPath;
import payload.AddPlace;

public class ComplexJsonParse {

	public static void main(String[] args) {
		/*
		1. Print No of courses returned by API

		2.Print Purchase Amount

		3. Print Title of the first course

		4. Print All course titles and their respective Prices

		5. Print no of copies sold by RPA Course

		6. Verify if Sum of all Course prices matches with Purchase Amount  */

		JsonPath jp = new JsonPath(AddPlace.coursesprice());

		// 1. Print No of courses returned by API
		int count = jp.getInt("courses.size()");
		System.out.println("Course Count : " +count);

		// 	2.Print Purchase Amount
		int purchaseAmount = jp.getInt("dashboard.purchaseAmount");
		System.out.println("purchaseAmount : "+purchaseAmount);

		//  3. Print Title of the first course
		String fristTitleCourse =(jp.getString("courses[0].title"));
		System.out.println(fristTitleCourse);

		//  4. Print All course titles and their respective Prices
		for(int i=0 ;i<count; i++) {
			String title = jp.getString("courses["+ i +"].title");
			System.out.println("Courses Names : "+title);
			String coursePrice = jp.getString("courses["+ i +"].price");
			System.out.println("Courses Price  : "+coursePrice);
		}

		//  Print no of copies sold by RPA Course
		for(int i=0 ;i<count; i++) {
			String title = jp.getString("courses["+ i +"].title");
			//	System.out.println("Courses Names : "+title);
			if(title.equalsIgnoreCase("RPA")) {
				System.out.println("Course Copies : "+jp.getString("courses["+ i +"].copies"));
			}
		}

		// 6. Verify if Sum of all Course prices matches with Purchase Amount  */

		int total_amount=0;
		for(int i=0 ;i<count; i++) {
			int copies_count = jp.getInt("courses["+ i +"].copies");
			int coursePrice = jp.getInt("courses["+ i +"].price");
			int course_sold= copies_count*coursePrice;
			total_amount =  total_amount+course_sold;
		}
		System.out.println("total_amount purchse : "+total_amount);
		if(purchaseAmount==total_amount) {
			System.out.println("Price matches");
		}
			

	}

}
