package practice_smoketests;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ReusableMethods;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PartiallyUpdateBooking extends HerOkuAppBaseUrl {

       /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
        "firstname" : "Mehmet",
        "lastname" : "Can"
        }
    When
        Send patch request
    Then
        Status code is 200
    And
        Body:
        {
        "firstname" : "Mehmet",
        "lastname" : "Can",
        "totalprice" : 100,
        "depositpaid" : true,
        "bookingdates" : {
            "checkin" : "2018-01-01",
            "checkout" : "2019-01-01"
        },
        "additionalneeds" : "Dinner"
        }
     */

    @Test
    public void test01() {

        //set the url
        spec.pathParams("first", "booking", "second", CreateBooking.bookingid);

        //set the expected data
        Map<String, Object> payload = ReusableMethods.jsonToMap("{\n" +
                "        \"firstname\" : \"Mehmet\",\n" +
                "        \"lastname\" : \"Can\"\n" +
                "        }");


        //send request get response
        Response response = given(spec).body(payload).patch("{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(),200);
        softAssert.assertEquals(actualData.get("firstname"),payload.get("firstname"));
        softAssert.assertEquals(actualData.get("lastname"),payload.get("lastname"));
        softAssert.assertAll();

    }
}