package get_requests;

import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get02 {

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

    //400 lü responselarda  HttpResponseException hatasi almamak icin
    // <argLine>-Duser.language=en</argLine> bu kodu pom xml de properties tagi icine eklemeliyiz
    //body() methodu icinde Hamcrest kütüphanesinin Matchers clasindan fayadalanar
    // assertionlar yapabiliriz, containsString() methodu parantez icinde belirrttigimiz strin ifadenin body de bulunup bulunmadigini test eder
    //var ise test gecer yok ise test kalir
    @Test
    public void test01() {

        //set the url
        String url = "https://petstore.swagger.io/v2/pet/0";

        //set the expected data
        //send request get response
        //do assertion
        given()
                .when()
                .get(url)
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .body(containsString("Pet not found"))
                .body(not(containsString("TechProEd")))
                .header("Server", "Jetty(9.2.9.v20150224)");

    }


    //alternative way
    @Test
    public void test02() {
        //set the url
        String url = "https://petstore.swagger.io/v2/pet/0";
        Response response = given()
                .when()
                .get(url);

        response.prettyPrint();

        response.then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
        ;


        //not --->  response.asString() methodu responsun body kısmını stringe dönüştürür
        Assert.assertTrue(response.asString().contains("Pet not found"));
        Assert.assertFalse(response.asString().contains("TechProEd"));

    }
}