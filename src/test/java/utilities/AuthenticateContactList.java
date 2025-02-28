package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthenticateContactList {

    /*
    Bu classi olusturmaktaki amacimizi :
    Contactlist api ile calisirken ihtiyac duyabileceğimiz tokeni
    dynamic olarak olusturmak ve onu return etmektir
     */


    public static String generateToken(){
        //set the url
        String url ="https://thinking-tester-contact-list.herokuapp.com/users/login";
        //set the payload / expected data
        String payload ="{\n" +
                "    \"email\": \"molu@gmail.com\",\n" +
                "    \"password\": \"1234567890\"\n" +
                "}";
        //send request get response
        Response response = given().body(payload).contentType(ContentType.JSON).when().post(url);
        return response.jsonPath().getString("token");
    }
}
