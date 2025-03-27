package practice_smoketests;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.HerOkuAppBookingDatesPojo;
import pojos.HerOkuAppGetResponsePojo;
import pojos.HerOkuAppPostResponsePojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GetBooking extends HerOkuAppBaseUrl {
    /*
    Given
         https://restful-booker.herokuapp.com/booking/:id
    When
         Send get request
    Then
         Status code is 200
    And
    Body: {
        "firstname": "Jim",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
        "checkin": "2018-01-01",
                "checkout": "2019-01-01"
    },
        "additionalneeds": "Breakfast"
    }
     */

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first", "booking", "second", CreateBooking.bookingid);

        //set the expected data
        HerOkuAppBookingDatesPojo bookingDatesPojo =
                new HerOkuAppBookingDatesPojo("2018-01-01", "2019-01-01");
        HerOkuAppGetResponsePojo expectedData =
                new HerOkuAppGetResponsePojo("Jim", "Brown", 111, true, bookingDatesPojo, "Breakfast");

        //send request get response
        Response response = given(spec)
                .when()
                .get("{first}/{second}");

        response.prettyPrint();

        //do assertion
        HerOkuAppGetResponsePojo actualData = response.as(HerOkuAppGetResponsePojo.class);

        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getLastname(), expectedData.getLastname());
        assertEquals(actualData.getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid());
        assertEquals(actualData.getBookingdates().getCheckin(), bookingDatesPojo.getCheckin());
        assertEquals(actualData.getBookingdates().getCheckout(), bookingDatesPojo.getCheckout());
        assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds());

    }
}