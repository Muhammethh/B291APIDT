package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticateHerOkuApp {


    public static String generateToken(){

        //set the url
        String url="https://restful-booker.herokuapp.com/auth";

        //set the payload
        String credentials = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        //send request get response
        Response response = given().body(credentials).contentType(ContentType.JSON).when().post(url);

        //get and return the token from response
        JsonPath jsonPath = response.jsonPath();
        return  jsonPath.getString("token");
    }

}