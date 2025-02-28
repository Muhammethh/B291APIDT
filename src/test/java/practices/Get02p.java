package practices;

import base_url.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get02p extends PetStoreBaseUrl {


     /*
        Given
            https://petstore.swagger.io/v2/pet/0
        When
            Kullanıcı URL'e bir GET request gönderir
        Then
            HTTP Status code 404 olmalı
        And
            Status Line "HTTP/1.1 404 Not Found" olmalı
        And
            Response body "Pet not found" içermeli
        And
            Response body "TechProEd" içermemeli
        And
            Server değeri "Jetty(9.2.9.v20150224)" olmalı
*/


    //set the url
    //set the payload/ expected data
    //send the request get respond
    //do assertion


    @Test
    public void test01() {

        spec.pathParams("first","pet","second",0);

        Response response = given(spec)
                .when()
                .get("{first}/{second}");
                response.prettyPrint();

        response.then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .body(Matchers.containsString("Pet not found"))
                .body(not(Matchers.containsString("TechProEd")))
                .header("Server","Jetty(9.2.9.v20150224)");


    }
}
