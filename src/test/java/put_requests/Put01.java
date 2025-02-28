package put_requests;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {

    /*
        Given
            1) https://jsonplaceholder.typicode.com/todos/198
            2)
        When
            Kullanıcı URL'e bir PUT request gönderir
        Then
           Status code 200 olmalı
           Response şu şekilde olmalı:
           {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
                "id": 198
           }
     */

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first", "todos", "second", 198);

        //set the expected data / payload
        Map<String, Object> payload = new HashMap<>();

        payload.put("userId", 21);
        payload.put("title", "Wash the dishes");
        payload.put("completed", false);
        System.out.println("payload = " + payload);


        //send request get response
        Response response = given(spec)
                .body(payload)
                .when()
                .put("{first}/{second}");
        response.prettyPrint();

        //do assertions
        //response.as(HashMap.class); metodu ile Json formatında ki responsu, hashmape dönüştürdük
        Map<String, Object> actualData = response.as(HashMap.class);//De-serialization

        assertEquals(actualData.get("userId"),payload.get("userId"));
        assertEquals(actualData.get("title"),payload.get("title"));
        assertEquals(actualData.get("completed"),payload.get("completed"));
        assertEquals(actualData.get("id"),198);


    }
}