package practices;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get03p extends PetStoreBaseUrl {


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

        spec.pathParams("first","pet","second", 291);

        Response response = given(spec)
                .when()
                .get("{first}/{second}");

        response.prettyPrint();

        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", Matchers.containsString("ELMAS"))
                .body("status", Matchers.equalTo("available"))
                .body("category.name", Matchers.equalTo("CAT"))
                .body("tags[0].id", Matchers.equalTo(0));

    }
}
