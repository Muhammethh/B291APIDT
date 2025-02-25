package get_requests;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get03 {

       /*
        Given
            https://petstore.swagger.io/v2/pet/291
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status Code 200 olmalı
        And
            Content Type "application/json" olmalı
        And
            "name" şu metni içermeli: "ELMAS",
        And
            "status" değeri "available" olmalı
        And
            "category" altındaki "name" değeri "CAT" olmalı
        And
            "tags" altındaki ilk datanin "name" değeri "bird" olmalı
     */

    @Test
    public void test01() {

        //set the url
        String url ="https://petstore.swagger.io/v2/pet/291";

        //set the expected data
        //send request get response
        //do assertion
        given()
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", containsString("ELMAS"))
                .body("status",equalTo("available"))
                .body("category.name",equalTo("CAT"))
                .body("tags[0].name",equalTo("bird"))
                .log().body();

    }


}