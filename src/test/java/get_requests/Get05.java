package get_requests;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get05 extends PetStoreBaseUrl {

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

        //set the url
        spec.pathParams("first","pet","second","findByStatus")
                .queryParam("status","available");

        //set the expected data
        //send request get response
        //do assertion
        given(spec)
                .when()
                .get("{first}/{second}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",hasItem(313))
                //response listimizde json datalarinin id field larinin valuesu icinde
                // 313 degerinin olup olmadigini test ediyoruz
                .body("name",hasItem("ELMAS"))
                .body("name",hasItems("ELMAS","doggie","fish"))
                .body("id",hasSize(greaterThan(200)))
                .body("id",hasSize(lessThan(500)))
                .body("[0].category.id",equalTo(0))
                .body("[0].photoUrls[0]",equalTo("string"))
                .body("[0].tags[0].id",equalTo(0))
                .log().body();

    }
}