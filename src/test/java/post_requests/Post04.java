package post_requests;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPayloadPojo;

import static io.restassured.RestAssured.given;

public class Post04 extends JsonPlaceHolderBaseUrl {

      /*
    Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
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
    public void test01() {
        //set the url
        spec.pathParam("first", "todos");

        //set the expected data / payload
        JsonPlaceHolderPayloadPojo payloadPojo =
                new JsonPlaceHolderPayloadPojo(55, "Tidy your room", false);

        //send request get response
        Response response = given(spec).body(payloadPojo).when().post("{first}");
        response.prettyPrint();

        //do assertion
        JsonPlaceHolderPayloadPojo actualData = response.as(JsonPlaceHolderPayloadPojo.class);
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(actualData.getUserId(), payloadPojo.getUserId());
        Assert.assertEquals(actualData.getTitle(), payloadPojo.getTitle());
        Assert.assertEquals(actualData.getCompleted(), payloadPojo.getCompleted());
    }
}