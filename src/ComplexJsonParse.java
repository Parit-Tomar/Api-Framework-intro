
import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		JsonPath js=new JsonPath(payload.CoursePrice());
		// Print No. of Courses Returned by Api
		
		int count= js.getInt("courses.size()");
		System.out.println(count);
		
		//Print Purhase Amount
		int totalAmount= js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//Print Title of First Course
		String titleFirstCourse=js.get("courses[0].title");
		System.out.println(titleFirstCourse);
		
		//Print All courses and their Prices
		for(int i=0;i<count;i++)
		{
			String courseTitles=js.get("courses["+i+"].title");
			System.out.println(courseTitles);
			System.out.println(js.get("courses["+i+"].price").toString());
		}
		
		//Print no. of Copies sold by RPA 
		System.out.println("Print no of copies sold by RPA Course");
		for(int i=0;i<count;i++)
		{
			String courseTitles=js.get("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA"))
			{
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		
		
	}

}
