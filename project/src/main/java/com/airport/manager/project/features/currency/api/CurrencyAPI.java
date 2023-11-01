package com.airport.manager.project.features.currency.api;

import lombok.NoArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
@Component
public final class CurrencyAPI {
    private static final String URL = "http://api.nbp.pl/api/exchangerates/rates/c/";
    private static CurrencyAPI instance = null;

    public static CurrencyAPI getInstance() {
        if (instance == null) {
            instance = new CurrencyAPI();
        }
        return instance;
    }

    public JSONObject getExchangeRate(String code) {
        String modifiedUrl = URL + code + "/?format=json";

        RestTemplate restTemplate = new RestTemplate();
        String response = "{}";
        try {
            response = restTemplate.getForObject(modifiedUrl, String.class);
        } catch (Exception e) {
            System.out.println("Unable to handle Currency API request: " + e.getMessage());
        }

        JSONObject jsonResponse = new JSONObject(response);
        return jsonResponse;
    }


}
