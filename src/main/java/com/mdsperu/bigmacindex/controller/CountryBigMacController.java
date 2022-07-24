package com.mdsperu.bigmacindex.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdsperu.bigmacindex.enums.CurrencyCode;
import com.mdsperu.bigmacindex.model.CountryBigMac;
import com.mdsperu.bigmacindex.service.CountryBigMacServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class CountryBigMacController {

    CountryBigMacServiceImpl countryBigMacServiceImpl;

    @Autowired
    public void setCountryBigMacServiceImpl(CountryBigMacServiceImpl countryBigMacServiceImpl) {
        this.countryBigMacServiceImpl = countryBigMacServiceImpl;
    }

    private final Logger log = getLogger(CountryBigMacController.class.getName());

    public String getUpdatedRatesFromApi() {
        String url = "https://openexchangerates.org/api/latest.json?app_id=955605c9e4764913b707dd547bf2fa66";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        boolean success = updateCurrencies(response);
        return response;
    }

    public boolean updateCurrencies(String json) {
        boolean success = false;
        ObjectMapper mapper = new ObjectMapper();
        LocalDateTime lastUpdate = LocalDateTime.now();
        try {
            JsonNode rates = mapper.readTree(json).get("rates");
            for (CurrencyCode currencyCode : CurrencyCode.values()) {

                JsonNode rate = rates.get(currencyCode.name());
                if (rate != null) {
                    BigDecimal exchangeRate = new BigDecimal(rate.asText());
                    if (countryBigMacServiceImpl.existsByCurrency(currencyCode)) {
                        CountryBigMac countryBigMac = countryBigMacServiceImpl.getCountryBigMacByCurrencyCode(currencyCode);
                        countryBigMac.setLastExchangeRateUpdated(lastUpdate);
                        countryBigMac.setLastExchangeRate(exchangeRate);
                        countryBigMacServiceImpl.save(countryBigMac);
                        log.warn("updating exchange rate for EXISTING country: {}", currencyCode);
                    } else {
                        CountryBigMac countryBigMac = new CountryBigMac();
                        countryBigMac.setCurrencyCode(currencyCode);
                        countryBigMac.setLastExchangeRate(exchangeRate);
                        countryBigMac.setLastExchangeRateUpdated(lastUpdate);
                        countryBigMacServiceImpl.save(countryBigMac);
                        log.warn("updating exchange rate for NEW country: {}", currencyCode);
                    }
                } else {
                    log.warn("no exchange rate for currency: {}", currencyCode);
                }
                success = true;
            }
        } catch (Exception e) {
            log.error("error updating currencies", e);
        }
        return success;
    }
}
