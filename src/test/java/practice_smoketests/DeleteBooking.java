package practice_smoketests;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteBooking extends HerOkuAppBaseUrl {
/*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Send delete request
    Then
        Status code is 200
    And
        Body should be : "Created"
     */

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first", "booking", "second", CreateBooking.bookingid);

        //set the expected data
        String expectedData ="Created";

        //send request get response
        Response response = given(spec).when().delete("{first}/{second}");

        //do assertion
        response.then().statusCode(201);
        Assert.assertEquals(  response.asString()  ,expectedData);

    }
}