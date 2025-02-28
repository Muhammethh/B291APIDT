package get_requests;

import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Get08 extends JsonPlaceHolderBaseUrl {

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
        //set the url
        spec.pathParam("first", "todos");

        //set the expected data
        //send request get response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        //do assertion
        response.then().statusCode(200);


        JsonPath jsonPath = response.jsonPath();
        //bu method response bodyde bulunan json datalarin bulundugu listin icindeki id valuelarini alarak
        //bir list olusturur
        List<Object> list = jsonPath.getList("id");
        System.out.println("list = " + list);

        List<Object> list1 = jsonPath.getList("title");
        System.out.println("list1 = " + list1);

        //groovy
        //"Id"leri 190 dan büyük olanları konsola yazdırın
        List<Object> list2 = jsonPath.getList("findAll{it.id>190}");
        System.out.println("list2 = " + list2);

        /*
        list2 =
        [
        {userId=10, id=191, title=temporibus atque distinctio omnis eius impedit tempore molestias pariatur, completed=true},
        {userId=10, id=192, title=ut quas possimus exercitationem sint voluptates, completed=false},
        {userId=10, id=193, title=rerum debitis voluptatem qui eveniet tempora distinctio a, completed=true},
        {userId=10, id=194, title=sed ut vero sit molestiae, completed=false},
        {userId=10, id=195, title=rerum ex veniam mollitia voluptatibus pariatur, completed=true},
        {userId=10, id=196, title=consequuntur aut ut fugit similique, completed=true},
        {userId=10, id=197, title=dignissimos quo nobis earum saepe, completed=true},
        {userId=10, id=198, title=quis eius est sint explicabo, completed=true},
        {userId=10, id=199, title=numquam repellendus a magnam, completed=true},
        {userId=10, id=200, title=ipsam aperiam voluptates qui, completed=false}
        ]
         */


        //"Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
        Assert.assertEquals(list2.size(), 10);


        //"Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
        List<Object> list3 = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("list3 = " + list3);  // [1, 1, 1, 1]

        //"Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
        Assert.assertEquals(list3.size(), 4);

        //"Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
        List<Object> list4 = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("list4 = " + list4);

        //"delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
        Assert.assertTrue(list4.contains("delectus aut autem"));


    }
}