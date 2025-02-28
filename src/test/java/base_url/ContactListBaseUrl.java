package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import utilities.AuthenticateContactList;

public class ContactListBaseUrl {


    //Bu classi olusturmaktaki amacimiz :
    // Her test ten önce calisiarak baseurl ve content type ..gibi ortak request yapilandirmalarini
    //tek bir merkezden yapabilmek ve böylece testlerin bakimini kolaylastirmaktir.

    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .addHeader("Authorization", AuthenticateContactList.generateToken())
                .setContentType(ContentType.JSON)
                .build();
    }


}