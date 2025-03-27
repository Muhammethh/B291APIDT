package practices;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get09p extends HerOkuAppBaseUrl {


    /*
     Given
         https://restful-booker.herokuapp.com/booking/2008
     When
         I send GET Request to the url
     Then
         Response body should be like that;
         {
             "firstname": "John",
             "lastname": "Smith",
             "totalprice": 111,
             "depositpaid": true,
             "bookingdates": {
                 "checkin": "2018-01-01",
                 "checkout": "2019-01-01"
             },
             "additionalneeds": "Dinner"
         }
     */


    @Test
    public void test01() {

        spec.pathParams("first","booking","second",1135);

        Response response = given(spec)
                .when()
                .get("{first}/{second}");

        response.prettyPrint();


    }
}
