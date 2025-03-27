package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPayloadPojo {
    /*
    Eger Olusturdugumuz pojo ile response tan gelen data tam olarak uyumlu degilse
    ve response ta fazladan baska fieldlar var ise bu alanlari ignore etmemiz gerekir
    bunun icin @JsonIgnoreProperties(ignoreUnknown = true) kodunu classin basina eklememiz yeterlidir
     */

    /*
    Amacimiz : Belirli bir cerceveye veya kısıtlamaya bagli kalmadana
    basit bagimsiz yeniden kullanilabilir, veri tasiyici objectler olusturmaktir
    POJO (==> Plain Old Java Object)
    Icinde
    1)private variablellar
    2)parametreli ve parametresiz constructorlar
    3)getterlar ve setterlar
    4)toString methodu
    barindiran classlara POJO class diyoruz
    NOT==>Private variabllar olusturulurken json formatta bulunan keyler birebir ayni
    sekilde yazilmalidir, bu keyin valuesuna göre datatype secilmelidir
     */

    private Integer userId;
    private String title;
    private Boolean completed;

    public JsonPlaceHolderPayloadPojo() {
        //Serialization ve De serialization islemleri icin arka planda ihtiyac duyuluyor
    }

    public JsonPlaceHolderPayloadPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "JsonPlaceHolderPayloadPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}