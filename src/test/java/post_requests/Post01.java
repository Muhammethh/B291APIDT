package post_requests;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Post01 extends PetStoreBaseUrl {
     /*
        Given
            https://petstore.swagger.io/v2/pet
        And
            {
                "id": 313,
                "category": {
                    "id": 0,
                    "name": "CAT"
                },
                "name": "Tekir",
                "photoUrls": [
                    "string"
                ],
                "tags": [
                    {
                        "id": 0,
                        "name": "bird"
                    }
                ],
                "status": "available"
            }
        When
            Kullanıcı URL'e bir POST request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type "application/json" olmalı
     */

    @Test
    public void test01() {

        //set the url
        spec.pathParam("first", "pet");
        //set the payload / expected data
        String payload = "{\n" +
                "                \"id\": 313,\n" +
                "                \"category\": {\n" +
                "                    \"id\": 0,\n" +
                "                    \"name\": \"CAT\"\n" +
                "                },\n" +
                "                \"name\": \"Tekir\",\n" +
                "                \"photoUrls\": [\n" +
                "                    \"string\"\n" +
                "                ],\n" +
                "                \"tags\": [\n" +
                "                    {\n" +
                "                        \"id\": 0,\n" +
                "                        \"name\": \"bird\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"status\": \"available\"\n" +
                "            }";
        //send request get response
        Response response = given(spec)
                .body(payload)
                .when()
                .post("{first}");

        //do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);


    }
}