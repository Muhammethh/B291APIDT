package practices;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Get08p extends JsonPlaceHolderBaseUrl {


       /*
    Given
       https://jsonplaceholder.typicode.com/todos
    When
       Kullanıcı URL'e bir GET request gönderir
    Then
        Status code 200 olmalı
        "Id"leri 190 dan büyük olanları konsola yazdırın
        "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
        "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
        "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
        "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
        "delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
 */


    @Test
    public void test01() {

        spec.pathParam("first", "todos");

        Response response = given(spec)
                .when()
                .get("{first}");

        response.prettyPrint();

        response.then()
                .statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        System.out.println("jsonPath.getList(\"id>190\") = " + jsonPath.getList("findAll{it.id>190}"));

        List<Object> list = jsonPath.getList("findAll{it.id>190}");

        Assert.assertEquals(list.size(),10);

        List<Object> list1 = jsonPath.getList("findAll{it.id<5}.userId");

        System.out.println("list1 = " + list1);

        Assert.assertEquals(list1.size(),4);

        List<Object> list2 = jsonPath.getList("findAll{it.id<5}.title");

        System.out.println("list2 = " + list2);

        Assert.assertTrue(list2.contains("delectus aut autem"));

    }
}
