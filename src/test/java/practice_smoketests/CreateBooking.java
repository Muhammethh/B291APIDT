package practice_smoketests;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.HerOkuAppBookingDatesPojo;
import pojos.HerOkuAppGetResponsePojo;
import pojos.HerOkuAppPostResponsePojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateBooking extends HerOkuAppBaseUrl {
    /*
 Given
     https://restful-booker.herokuapp.com/booking
 And
     {
         "firstname" : "Jim",
         "lastname" : "Brown",
         "totalprice" : 111,
         "depositpaid" : true,
         "bookingdates" : {
             "checkin" : "2018-01-01",
             "checkout" : "2019-01-01"
         },
         "additionalneeds" : "Breakfast"
     }
  When
     Send post request
  Then
     Status code is 200
  And
     Body:
      {
         "bookingid": 1,
         "booking": {
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
     }
  */


    /*
    Bu classta olusturdugumuz bookingid yi diger classlardan da kullanabilmek ve böylece testlerimizi kendi olusturdugumuz
    data üzerinden gerceklestirebilmek icin static variable a assign ettik
     */
    static Integer bookingid ;
    @Test
    public void test01() {
        //set the url
        spec.pathParam("first", "booking");
        //set the payload / expected data
        HerOkuAppBookingDatesPojo bookingDatesPojo =
                new HerOkuAppBookingDatesPojo("2018-01-01", "2019-01-01");
        HerOkuAppGetResponsePojo expectedData =
                new HerOkuAppGetResponsePojo("Jim", "Brown", 111, true, bookingDatesPojo, "Breakfast");
        //send request get response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();
        //do assertion
        HerOkuAppPostResponsePojo actualData = response.as(HerOkuAppPostResponsePojo.class);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getBooking().getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getBooking().getLastname(), expectedData.getLastname());
        assertEquals(actualData.getBooking().getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getBooking().getDepositpaid(), expectedData.getDepositpaid());
        assertEquals(actualData.getBooking().getBookingdates().getCheckin(), bookingDatesPojo.getCheckin());
        assertEquals(actualData.getBooking().getBookingdates().getCheckout(), bookingDatesPojo.getCheckout());
        assertEquals(actualData.getBooking().getAdditionalneeds(), expectedData.getAdditionalneeds());

        //birinci yol
        bookingid = actualData.getBookingid();
        System.out.println("bookingid = " + bookingid);
        //ikinci yol
        int bookingId2 = response.jsonPath().getInt("bookingid");
        System.out.println("bookingId2 = " + bookingId2);
    }
}