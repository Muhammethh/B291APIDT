package get_requests;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get06 extends HerOkuAppBaseUrl {

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

    //first way hamcrest matchers
    @Test
    public void test01() {

        //set the url
        spec.pathParams("first", "booking", "second", 3774);

        //set the expected data / payload
        //send request get response
        given(spec)
                .when()
                .get("{first}/{second}")
                .then() //do assertion
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Jim"))
                .body("lastname", equalTo("Brown"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("Breakfast"));
    }


    //SECOND WAY jsonpath
    @Test
    public void test02() {
        //set the url
        spec.pathParams("first", "booking", "second", 3678);

        //set the expected data / payload
        //send request get response
        Response response = given(spec)
                .when()
                .get("{first}/{second}");

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();//response body kısmını jsonpath objectine dönüstürür

        int totalPrice = jsonPath.getInt("totalprice");
        System.out.println("totalPrice = " + totalPrice);

        boolean depositpaid = jsonPath.getBoolean("depositpaid");
        System.out.println("depositpaid = " + depositpaid);

        String firstname = jsonPath.getString("firstname");
        System.out.println("firstname = " + firstname);

        /* //////////////// jsonpath ////////////////// */
        //do assertion

        Assert.assertEquals(jsonPath.getString("firstname"),"Jim");
        Assert.assertEquals(jsonPath.getString("lastname"),"Brown");
        Assert.assertEquals(jsonPath.getInt("totalprice"),111);
        Assert.assertTrue(jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01");
        Assert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2019-01-01");
        Assert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast");

    }





}