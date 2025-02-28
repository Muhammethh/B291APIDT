package get_requests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Get01 {

    //Api görevi : clientdan aldığı requesti databasee yollar ve databaseden aldığı responseu clienta geri döndürür


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

        //1)set the url
        String url ="https://petstore.swagger.io/v2/pet/5";

        //2)set the expected data
        //3)send request get response
        Response response = given().when().get(url);
        response.prettyPrint();

        //4)do assertion
        response.then().assertThat().statusCode(200)// status codun 200 oldugunu doğrular
                .and().assertThat().contentType("application/json")//content typenin application/json oldugunu doğrular
                .and().assertThat().statusLine("HTTP/1.1 200 OK");//status linein HTTP/1.1 200 OK olduunu doğrular
    }


    //ikinci yol
    @Test
    public void test02() {

        //1)set the url
        String url ="https://petstore.swagger.io/v2/pet/5";

        //2)set the expected data
        //3)send request get response
        Response response = given().
                when().
                get(url);

        // response.prettyPrint();

        //4)do assertion
        response.
                then().
                statusCode(200)// status codun 200 oldugunu doğrular
                .contentType("application/json")//content typenin application/json oldugunu doğrular
                .statusLine("HTTP/1.1 200 OK")//status linein HTTP/1.1 200 OK olduunu doğrular
                //.log().body()//response un body kısmını console yazdirir
                .log().all();
    }





}