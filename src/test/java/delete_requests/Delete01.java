package delete_requests;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Delete01 extends JsonPlaceHolderBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
            I send DELETE Request to the Url
        Then
            Status code is 200
        And Response body is { }
    */

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first", "todos", "second", 198);

        //set the expected data  / payload
        Map<String, Object> expectedData = new HashMap<>();
        System.out.println("expectedData = " + expectedData);

        //send request get response
        Response response = given(spec).when().delete("{first}/{second}");

        //do assertion
        Map<String, Object> actualData = response.as(HashMap.class);

        //1 yol
        Assert.assertEquals(actualData,expectedData);

        //2.yol
        Assert.assertTrue(actualData.isEmpty());

        //3.yol
        Assert.assertEquals(actualData.size(),0);
    }


}