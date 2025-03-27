package practice_smoketests;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojos.HerOkuAppBookingDatesPojo;
import pojos.HerOkuAppGetResponsePojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class UpdateBooking extends HerOkuAppBaseUrl {

     /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
        "firstname" : "Ali",
        "lastname" : "Can",
        "totalprice" : 100,
        "depositpaid" : true,
        "bookingdates" : {
            "checkin" : "2018-01-01",
            "checkout" : "2019-01-01"
        },
        "additionalneeds" : "Dinner"
        }
    When
        Send put request
    Then
        Status code is 200
    And
        Body:
        {
        "firstname" : "Ali",
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
        spec.pathParams("first","booking","second",CreateBooking.bookingid);

        //set the expected data
        HerOkuAppBookingDatesPojo bookingDatesPojo =
                new HerOkuAppBookingDatesPojo("2018-01-01", "2019-01-01");
        HerOkuAppGetResponsePojo expectedData =
                new HerOkuAppGetResponsePojo("Ali", "Can", 100, true, bookingDatesPojo, "Dinner");

        //send request get response
        Response response = given(spec)
                .body(expectedData)
                .when()
                .put("{first}/{second}");

        //do assertion
        HerOkuAppGetResponsePojo actualData = response.as(HerOkuAppGetResponsePojo.class);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.statusCode(),200);
        softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname());
        softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname());
        softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
        softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
        softAssert.assertEquals(actualData.getBookingdates().getCheckin(),bookingDatesPojo.getCheckin());
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(),bookingDatesPojo.getCheckout());
        softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());

        softAssert.assertAll();

    }
}