package api;

import java.io.IOException;

import org.json.simple.JSONObject;


import  io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SendPostRequest
{
    public static void main(String[] args) throws IOException 
    {

        RestAssured.baseURI ="http://localhost:4567";
        RequestSpecification request = RestAssured.given();
            JSONObject json = new JSONObject();
            json.put("username","saitx");
            json.put("text", "Something Something Darkside");
           // json.put("timeout","60");
            request.header("Content-Type", "application/json");
            request.body(json.toJSONString());
            Response response = request.post("/chat");
            
            int statusCode = response.getStatusCode();
            System.out.println("The status code recieved: " + statusCode);
         
            System.out.println("Response body: " + response.body().asString());
        
    }
}
