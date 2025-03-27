package get_requests;

import base_url.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.HerOkuAppBookingDatesPojo;
import pojos.HerOkuAppGetResponsePojo;

import static io.restassured.RestAssured.given;

public class Get10 extends HerOkuAppBaseUrl {
    /*
 Given
     https://restful-booker.herokuapp.com/booking/3647
 When
     I send GET Request to the url
 Then
     Response body should be like that;
     {
         "firstname": "John",
         "lastname": "Smith",
         "totalprice": 111,
         "depositpaid": true,
         "ge": {
             "checkin": "2018-01-01",
             "checkout": "2019-01-01"
         },
         "additionalneeds": "Breakfast"
     }
 */

    @Test
    public void test01() {
        //set the url
        spec.pathParams("first","booking","second",3543);

        //set the expected data
        HerOkuAppBookingDatesPojo bookingDatesPojo
                = new HerOkuAppBookingDatesPojo("2018-01-01","2019-01-01");

        HerOkuAppGetResponsePojo expectedData =
                new HerOkuAppGetResponsePojo("John","Smith",111,true,bookingDatesPojo, "Breakfast");


        //send request get response
        Response response = given(spec).when().get("{first}/{second}");

        //do assertion
        HerOkuAppGetResponsePojo actualData = response.as(HerOkuAppGetResponsePojo.class);
        Assert.assertEquals(actualData.getFirstname(),expectedData.getFirstname());
        Assert.assertEquals(actualData.getLastname(),expectedData.getLastname());
        Assert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
        Assert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
        Assert.assertEquals(actualData.getBookingdates().getCheckin(),bookingDatesPojo.getCheckin());
        Assert.assertEquals(actualData.getBookingdates().getCheckout(),bookingDatesPojo.getCheckout());
        Assert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());

    }
}