package post_requests;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Post02 extends JsonPlaceHolderBaseUrl {

     /*
    Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }

        When
            Kullanıcı URL'e bir POST request gönderir
        Then
            Status code 201 olmalı
        And
            Response şu şekilde olmalı:
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
     */

    @Test
    public void test01() {

        //set the url
        spec.pathParam("first", "todos");

        //set the expected data / payload
        /*
        String kullanmak bir yöntemdir,
        ama assertion icin tavsiye edilmez

        String payload = "{\n" +
                "             \"userId\": 55,\n" +
                "             \"title\": \"Tidy your room\",\n" +
                "             \"completed\": false\n" +
                "           }"; */

        //JSON formatina en yakin bir payload/expecteddata olusturabilmek icin Map kullanabiliriz
        Map<String,Object> payload = new HashMap<>();
        payload.put("userId",55);
        payload.put("title","Tidy your room");
        payload.put("completed",false);
        System.out.println("payload = " + payload);
        /*{completed=false, title=Tidy your room, userId=55}*/

        //send request get response
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();

        /*
        Serialization : Java datalarimizin JSON datalarina dönüstürülme islemidir
        De-Serialization : JSON datalarinın Java datalarina dönüstürülme islemidir
        pom.xml dosyasi icine ekledigimiz Jackson Databind gibi kütüphaneler serialization ve deserialization islemlerini otomatik olarak gerceklestirir
        bizim extra bir sey yapmamiza gerek kalmaz
         */

        //do assertion
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("title"),payload.get("title"));
        Assert.assertEquals(jsonPath.getInt("userId"),payload.get("userId"));
        Assert.assertEquals(jsonPath.getBoolean("completed"),payload.get("completed"));
        Assert.assertEquals(jsonPath.getInt("id"),201);


    }
}