package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class ReusableMethods {


    //JSON  formatinda olan bir stringi Map e dönüstüren bir reusable method olusturmak
    public static Map<String, Object> jsonToMap(String json){

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



}