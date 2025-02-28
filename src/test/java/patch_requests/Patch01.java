package patch_requests;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class Patch01 extends JsonPlaceHolderBaseUrl {
        /*
    Given
        1) https://jsonplaceholder.typicode.com/todos/198
        2) {
              "title": "Wash the dishes"
           }
    When
      I send PATCH Request to the Url
    Then
          Status code is 200
          And response body is like
              {
                "userId": 10,
                "title": "Wash the dishes",
                "completed": true,
                "id": 198
              }
     */

    @Test
    public void test01() {

        //set the url
        spec.pathParams("first", "todos", "second", 198);

        //set the payload / expected data
        /*String payload ="{\n" +
                "              \"title\": \"Wash the dishes\"\n" +
                "           }";*/
        /*
        Patch requestlerde sadece değistirmek istedigmiz kadar kısmı yazmamamiz yeterlidir
        cünkü pathc partially  update yapmak icin kullanilir
         */
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", "Wash the dishes");
//        payload.put("completed",false);
//        payload.put("userId",5);


        //send request get response
        Response response = given(spec)
                .body(payload)
                .when()
                .patch("{first}/{second}");


        //payload bizim ayni zamanda expected datamizdir, bu nedenle
        //assertion amacli payload mapimize expected datayi set edebilirz
        payload.put("completed", true);
        payload.put("userId", 10);
        payload.put("id", 198);


        //do assertion
        response.then().statusCode(200);//1.yol
        Assert.assertEquals(response.statusCode(), 200);//2.yol

        Map<String, Object> actualData = response.as(HashMap.class);//de-serialization

        Assert.assertEquals(actualData.get("userId"),payload.get("userId"));
        Assert.assertEquals(actualData.get("title"),payload.get("title"));
        Assert.assertEquals(actualData.get("completed"),payload.get("completed"));
        Assert.assertEquals(actualData.get("id"),payload.get("id"));

    }
}