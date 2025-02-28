package get_requests;

import base_url.ContactListBaseUrl;
import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get07 extends ContactListBaseUrl {

    /*
    Given
        https://thinking-tester-contact-list.herokuapp.com/contacts
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
        spec.pathParam("first","contacts");

        //set the expected data
        //send request get response
        Response response = given(spec)
                .when()
                //.header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2I4M2M3ZjA3YzY0NTAwMTM3OTAwZDQiLCJpYXQiOjE3NDA1OTk5MDV9.9W1s4J8AP8K8v6MCqhPhurMt2U07pP3ChyOmH4IziBE")
                .get("{first}");

        //do assertion
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

}