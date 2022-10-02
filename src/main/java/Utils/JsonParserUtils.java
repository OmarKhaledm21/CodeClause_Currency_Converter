package Utils;

import Model.CurrencyPair;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonParserUtils {
    public static HashMap<String,String> parseCurrencyList(String responseBody){
        HashMap<String,String> code_to_name = new HashMap<>();
        JsonElement element = JsonParser.parseString(responseBody);
        JsonObject obj = element.getAsJsonObject(); //since you know it's a JsonObject
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();//will return members of your object
        for (Map.Entry<String, JsonElement> entry: entries) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue()).replace("\"","");
            code_to_name.put(key,value);
        }
        return code_to_name;
    }

    public static double parseConvert(String responseBody,String code){
        JsonElement element = JsonParser.parseString(responseBody);
        JsonObject object = element.getAsJsonObject();

        return Double.parseDouble(String.valueOf(object.get(code)));
    }
}
