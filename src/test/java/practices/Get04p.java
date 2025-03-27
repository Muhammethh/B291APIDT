package practices;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get04p extends PetStoreBaseUrl {

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

        spec.pathParams("first", "pet", "second", 313);

        Response response = given(spec)
                .when()
                .get("{first}/{second}");
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);

    }
}
