package API;

import Model.CurrencyPair;
import Utils.JsonParserUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;


public class API implements IAPI {
    public HashMap<String, String> getCurrencies() throws Exception {
        final String str_url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies.json";

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(str_url))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        return JsonParserUtils.parseCurrencyList(getResponse.body());
    }

    @Override
    public CurrencyPair convertCurrency(CurrencyPair currencyPair) throws Exception {
        final String str_url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + currencyPair.getFrom_code() + "/" + currencyPair.getTo_code() + ".json";
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(str_url))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        double rate = JsonParserUtils.parseConvert(getResponse.body(),currencyPair.getTo_code());
        currencyPair.setFrom_equivalent(1.0);
        currencyPair.setTo_equivalent(rate);
        System.out.println(rate);
        return currencyPair;
    }
}
