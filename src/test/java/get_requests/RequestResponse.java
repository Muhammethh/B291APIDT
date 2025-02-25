package get_requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class RequestResponse {

    /*
    1) Manual API testleri icin postman kullanmaya devam edeceğiz
       Otomasyon testleri icin Rest Assured Library kullanacağız
    2) RestAssured, gherkin dilinden faydalanarak hazir bazi methodlar olusturmustur.
        a) given    : Preconditions
        b) when     : Actions (get, post, put...)
        c) then     : Assertions
        d) and      : bu üc keyword kullaniminda tekrarlayan stepler icin kullanilir

    =NOT==> API otomasyon testlerini yazarken asagidaki adimlari takip edebiliriz
        1) Set the url (urli ayarlamaliyiz)
        2) Set the expected data (Beklenen datayi ayarla)
        3) Send request Get response (istek gönder, serverdan cevabi al)
        4) Do assertion ( response üzerinde doğrulamalar yapacağız )
     */

    @Test
    public void test01() {

        // 1) Set the url (urli ayarlamaliyiz)
        String url ="https://petstore.swagger.io/v2/pet/5";

        // 2) Set the expected data (Beklenen datayi ayarla)
        // 3) Send request Get response (istek gönder, serverdan cevabi al)
        // 4) Do assertion ( response üzerinde doğrulamalar yapacağız )

        Response response = given().when().get(url);
        response.print();
        response.prettyPrint();

        System.out.println("response.statusCode() = " + response.statusCode());//200
        System.out.println("response.statusLine() = " + response.statusLine());//HTTP/1.1 200 OK
        System.out.println("response.contentType() = " + response.contentType());//application/json
        System.out.println("response.headers() =\n" + response.headers());
        System.out.println("response.time() = " + response.time());


    }


}