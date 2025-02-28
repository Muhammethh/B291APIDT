package get_requests;

import base_url.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {


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
        //set the url
        spec.pathParams("first", "booking", "second", 1135);

        //set the expected data
        Map<String, String> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "John");
        expectedData.put("lastname", "Smith");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdates);
        expectedData.put("additionalneeds", "Dinner");
        System.out.println("expectedData = " + expectedData);


        //send request get response
        Response response = given(spec).when().get("{first}/{second}");

        //do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(actualData.get("firstname"), expectedData.get("firstname"));
        assertEquals(actualData.get("lastname"), expectedData.get("lastname"));
        assertEquals(actualData.get("totalprice"), expectedData.get("totalprice"));
        assertEquals(actualData.get("depositpaid"), expectedData.get("depositpaid"));
        assertEquals(actualData.get("additionalneeds"), expectedData.get("additionalneeds"));
        assertEquals(actualData.get("bookingdates"), expectedData.get("bookingdates"));


        /*
        Nested datalar ile calisirken jsonpath kullanilmasi tavsiye edilir, cünkü
        actualData mapimizin value kısmı object data tipindedir. Bu nedenle maplerde bulunan get methodu
        burada calismayacktir. Bu defa o objecti map a type casting yapmamiz gerekecek, buda codun okunmasini
        ve bakimini zorlastiracaktir,
         */
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getString("bookingdates.checkin"), bookingdates.get("checkin"));
        assertEquals(jsonPath.getString("bookingdates.checkout"), bookingdates.get("checkout"));

    }
}