package com.airport.manager.project.features.currency.controllers;

import com.airport.manager.project.features.currency.api.CurrencyAPI;
import com.airport.manager.project.features.currency.models.Test;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    private final CurrencyAPI currencyAPI;
    public CurrencyController(CurrencyAPI currencyAPI) {
        this.currencyAPI = currencyAPI;
    }

    @GetMapping("/exchange_rate")
    public void getExchangeRate(@RequestBody Test test) {
        JSONObject response = currencyAPI.getExchangeRate(test.getCode());
        System.out.println(response.getJSONArray("rates").toString());
    }

}
