package get_requests;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get04 extends PetStoreBaseUrl {
        /*
        Given
            https://petstore.swagger.io/v2/pet/313
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type "application/json" olmalı
    */

    @Test
    public void test01() {
        //set the url
        // String url ="https://petstore.swagger.io/v2/pet/313";
        spec.pathParams("first","pet","second",313);
        //set the expected data
        //send request get response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        //do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
}