import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.payload;
import Files.payloadcav;

public class basicwithoutfullbodycav {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//Add Place -> Update Place with new address -> Get Place to validate if New Address is present in response
		
		//given : All input details,
		//when : Submit the Api - Resource and htp method,
		//then : validate the response
		
		RestAssured.baseURI= "https://10.10.30.71:443";
		String response=given().relaxedHTTPSValidation().log().all().header("Content-Type", "application/json")
		.body(payloadcav.AddPlace()).when().post("/DashboardServer/web/AlertDataService/genCustomAlert")
		.then().assertThat().log().all().statusCode(200).body("statusCode", equalTo(201)).body("ruleName", equalTo("Parit_Custom_Postman"))
		.extract().response().asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response); //for parsing Json
		String alertId=js.getString("alertID");
		
		System.out.println(alertId);
		
		//Update Place 
		
		String newalertVal = "22.2";
		
		given().log().all().relaxedHTTPSValidation().header("Content-Type", "application/json")
		.body("{\r\n" + 
				"    \"trNum\": \"11155\",\r\n" + 
				"    \"alertMessage\": \"Custom alert\\n executed manually\",\r\n" + 
				"    \"alertSeverity\": \"2\",\r\n" + 
				"    \"thresholdValue\": \"-1.0\",\r\n" + 
				"    \"alertValue\": \""+newalertVal+"\",\r\n" + 
				"    \"alertValueStr\": \"Condition_1: AV-11\\nCondition_2: AV-13\",\r\n" + 
				"    \"prevAlertValue\": \"-1.0\",\r\n" + 
				"    \"prevSeverity\": \"1\",\r\n" + 
				"    \"state\": \"1\",\r\n" + 
				"    \"status\": \"4\",\r\n" + 
				"    \"alertId\": \"1111\",\r\n" + 
				"    \"ruleName\": \"Parit_Custom_Postman\",\r\n" + 
				"    \"tier\": \"AppTier\",\r\n" + 
				"    \"server\": \"QAServer40\",\r\n" + 
				"    \"instance\": \"-\",\r\n" + 
				"    \"ruleDesc\": \"-\",\r\n" + 
				"    \"vectorName\": \"AppTier>QAServer40\",\r\n" + 
				"    \"threshType\": \"1\",\r\n" + 
				"    \"alertType\": \"0\",\r\n" + 
				"    \"eventType\": \"1\",\r\n" + 
				"    \"minorThreshold\": \"2.0\",\r\n" + 
				"    \"majorThreshold\": \"5.0\",\r\n" + 
				"    \"criticalThreshold\": \"8.0\",\r\n" + 
				"    \"timeWindow\": \"60\",\r\n" + 
				"    \"numSamples\": \"1\",\r\n" + 
				"    \"timeZone\": \"Asia/Kolkata\",\r\n" + 
				"    \"topologyName\": \"ContinuousMonitoring\",\r\n" + 
				"    \"policyName\": \"ParitRest\",\r\n" + 
				"    \"sourceProtocol\": \"https\",\r\n" + 
				"    \"sourceIP\": \"172.18.0.1\",\r\n" + 
				"    \"sourcePort\": \"443\",\r\n" + 
				"    \"conditionList\": [\r\n" + 
				"        {\r\n" + 
				"            \"name\": \"Condition_1\",\r\n" + 
				"            \"conditionId\": 0,\r\n" + 
				"            \"baselineName\": \"NA\",\r\n" + 
				"            \"operator\": 8,\r\n" + 
				"            \"groupInfo\": {\r\n" + 
				"                \"id\": 10108,\r\n" + 
				"                \"name\": \"ba\"\r\n" + 
				"            },\r\n" + 
				"            \"graphInfo\": {\r\n" + 
				"                \"id\": 18,\r\n" + 
				"                \"name\": \"\"\r\n" + 
				"            },\r\n" + 
				"            \"conditionCommonVectorMap\": {\r\n" + 
				"                \"AppTier>QAServer40\": {\r\n" + 
				"                    \"currentValue\": 25.035,\r\n" + 
				"                    \"prevValue\": 0,\r\n" + 
				"                    \"totalVector\": 1,\r\n" + 
				"                    \"satisfied\": true,\r\n" + 
				"                    \"baselineValue\": 2,\r\n" + 
				"                    \"perVectorMap\": {\r\n" + 
				"                        \"AppTier>QAServer40\": {\r\n" + 
				"                            \"loadValue\": -1,\r\n" + 
				"                            \"satisfied\": true,\r\n" + 
				"                            \"currentValue\": 22.03,\r\n" + 
				"                            \"thresholdPerVectorMap\": {}\r\n" + 
				"                        }\r\n" + 
				"                    }\r\n" + 
				"                }\r\n" + 
				"            },\r\n" + 
				"            \"metaData\": \"Tier>Server\"\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"ruleExpression\": \"Condition_1\",\r\n" + 
				"    \"ruleLevel\": \"1\",\r\n" + 
				"    \"severityLevel\": \"1\",\r\n" + 
				"    \"derivedExpression\": \"-\",\r\n" + 
				"    \"eventState\": \"1\"\r\n" + 
				"}").
		when().post("/DashboardServer/web/AlertDataService/genCustomAlert")
		.then().log().all().assertThat().statusCode(200).body("statusCode", equalTo(201));
		
		//Get Place
		/*
		String getPlaceResponse=given().log().all().relaxedHTTPSValidation().queryParam("alertID",alertId)
		.when().get("/DashboardServer/web/AlertDataService/genCustomAlert")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1=new JsonPath(getPlaceResponse);
		String alertVal = js1.getString("alertVal");
		System.out.println(alertVal);
		Assert.assertEquals(alertVal, newalertVal);
		//Junit, Testng
		
		*/
	}

}