package post_requests;

import base_url.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ReusableMethods;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class Post03 extends JsonPlaceHolderBaseUrl {

    /*
     /*
        Given
            1) https://jsonplaceholder.typicode.com/todos
            2) {"userId": 55,"title": "Tidy your room","completed": false}
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
     */

    @Test
    public void test01() throws JsonProcessingException {
        //set the url
        spec.pathParam("first", "todos");

        //set the payload / expected data
        String json = "{\"userId\": 55,\"title\": \"Tidy your room\",\"completed\": false}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> payload = objectMapper.readValue(json, HashMap.class);
        System.out.println("payload = " + payload);
        /*
        import com.fasterxml.jackson.databind.ObjectMapper;
        kütüphanesinden ObjectMapper classindan bir object olusturduk
        bu obect üzerinden readvalue methodu sayesinde string olarak verdigimiz
        json formati map a dönüstürdük, böylece datalari manual olarak girmek zorunda kalmadik
         */

        //send request get response
        Response response = given(spec).body(payload).when().post("{first}");

        //do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        Map<String, Object> actualData2 = objectMapper.readValue(response.asString(), HashMap.class);
        System.out.println("actualData2 = " + actualData2);
        System.out.println("actualData = " + actualData);
        Assert.assertEquals(actualData.get("title"), payload.get("title"));
        Assert.assertEquals(actualData.get("completed"), payload.get("completed"));
        Assert.assertEquals(actualData.get("userId"), payload.get("userId"));
        Assert.assertEquals(actualData.get("id"), 201);
    }

    @Test
    public void test02() throws JsonProcessingException {
        //set the url
        spec.pathParam("first", "todos");

        //set the payload / expected data
        Map<String, Object> payload = ReusableMethods.jsonToMap("{\"userId\": 55,\"title\": \"Tidy your room\",\"completed\": false}");
        System.out.println("payload = " + payload);
        /*
        import com.fasterxml.jackson.databind.ObjectMapper;
        kütüphanesinden ObjectMapper classindan bir object olusturduk
        bu obect üzerinden readvalue methodu sayesinde string olarak verdigimiz
        json formati map a dönüstürdük, böylece datalari manual olarak girmek zorunda kalmadik
         */

        //send request get response
        Response response = given(spec).body(payload).when().post("{first}");

        //do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        //  Map<String, Object> actualData2 = objectMapper.readValue(response.asString(), HashMap.class);

        Assert.assertEquals(actualData.get("title"), payload.get("title"));
        Assert.assertEquals(actualData.get("completed"), payload.get("completed"));
        Assert.assertEquals(actualData.get("userId"), payload.get("userId"));
        Assert.assertEquals(actualData.get("id"), 201);
    }

}