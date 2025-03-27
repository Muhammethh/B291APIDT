package practices;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get05p extends PetStoreBaseUrl {

     /*
    Given
        https://petstore.swagger.io/v2/pet/findByStatus?status=available
    When
        Kullanıcı URL'e bir GET request gönderir
    Then
        HTTP Status Code 200 olmalı
    And
        Content Type "application/json" olmalı
    And
        Listede id değeri 313 olan bir eleman olmalı
    And
        Listede name değeri "ELMAS" olan bir eleman olmalı
    And
        Listede name değerleri "ELMAS", "doggie", "fish" olan elemanlar olmalı
    And
        Listede en az 200 tane eleman olmalı
    And
        Listede 500'den az eleman olmalı
    And
        Listenin ilk elemanının category - id değeri 0 olmalı
    And
        Listenin ilk elemanının photoUrls değeri "string" olmalı
    And
        Listenin ilk elemanının tags - id değeri 0 olmalı
 */


    @Test
    public void test01() {

        spec.pathParams("first","pet","second","findByStatus").queryParam("status","available");

        Response response = given(spec)
                .when()
                .get("{first}/{second}");

        response.prettyPrint();

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",Matchers.equalTo(313))
                .body("name",Matchers.equalTo("ELMAS"))
                .body("id",Matchers.hasItems("ELMAS","doggie","fish"));


    }
}
