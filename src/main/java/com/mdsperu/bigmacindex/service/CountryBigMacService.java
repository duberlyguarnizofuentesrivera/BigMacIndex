package com.mdsperu.bigmacindex.service;

import com.mdsperu.bigmacindex.enums.CurrencyCode;
import com.mdsperu.bigmacindex.model.CountryBigMac;

import java.math.BigDecimal;
import java.util.List;

public interface CountryBigMacService {
    CountryBigMac getCountryBigMacByCurrencyCode(CurrencyCode currencyCode);

    void updateLastExchangeRate(CountryBigMac countryBigMac, BigDecimal exchangeRate);

    void updateLocalPrice(CountryBigMac countryBigMac, BigDecimal localPrice);

    void updateUpdateUrl(CountryBigMac countryBigMac, String updateUrl);

    void updateWebComponent(CountryBigMac countryBigMac, String webComponent);

    void save(CountryBigMac countryBigMac);

    boolean existsByCurrency(CurrencyCode currencyCode);

    List<String[]> getAllRates();
}
