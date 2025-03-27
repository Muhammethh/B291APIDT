package practices;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get06p extends HerOkuAppBaseUrl {


     /*
        Given
            https://restful-booker.herokuapp.com/booking/23
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type should contain "application/json"
        And
            Response body should be like;
                {
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
        spec.pathParams("first", "booking", "second", 199);

        // HerOkuAppBookingDatesPojo herOkuAppBookingDatesPojo = new HerOkuAppBookingDatesPojo("2018-01-01","2019-01-01");
        // HerOkuAppGetResponsePojo payload = new HerOkuAppGetResponsePojo("Jim","Brown",111,);

        Response response = given(spec)
                .when()
                .get("{first}/{second}");
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", Matchers.equalTo("Jim"))
                .body("lastname", Matchers.equalTo("Brown"))
                .body("totalprice", Matchers.equalTo(111))
                .body("depositpaid", Matchers.equalTo(true))
                .body("bookingdates.checkin", Matchers.equalTo("2018-01-01"))
                .body("bookingdates.checkout", Matchers.equalTo("2019-01-01"))
                .body("additionalneeds", Matchers.equalTo("Breakfast"));


    }
}
