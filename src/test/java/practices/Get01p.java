package practices;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get01p {

    /*
     Given
         https://petstore.swagger.io/v2/pet/5
     When
         Kullanıcı URL'e bir GET request gönderir
     Then
         HTTP Status Code 200 olmalı
     And
         Content Type "application/json" olmalı
     And
         Status Line "HTTP/1.1 200 OK" olmalı
    */

    @Test
    public void test01() {

        String url = "https://petstore.swagger.io/v2/pet/5";

        given().when().get(url)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK");


    }
}
