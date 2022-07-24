package com.mdsperu.bigmacindex.service;

import com.mdsperu.bigmacindex.enums.Country;
import com.mdsperu.bigmacindex.enums.CurrencyCode;
import com.mdsperu.bigmacindex.model.CountryBigMac;
import com.mdsperu.bigmacindex.repository.CountryBigMacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CountryBigMacServiceImpl implements CountryBigMacService {

    private CountryBigMacRepository countryBigMacRepository;

    @Autowired
    public void setCountryBigMacRepository(CountryBigMacRepository countryBigMacRepository) {
        this.countryBigMacRepository = countryBigMacRepository;
        System.out.println("CountryBigMacServiceImpl.setCountryBigMacRepository");
    }

    @Override
    public CountryBigMac getCountryBigMacByCountryName(Country country) {
        List<CountryBigMac> countryBigMacList = countryBigMacRepository.findFirstByCountry(country);
        return countryBigMacList.get(0);
    }

    @Override
    public CountryBigMac getCountryBigMacByCurrencyCode(CurrencyCode currencyCode) {
        return null;
    }

    @Override
    public void updateLastExchangeRate(CountryBigMac countryBigMac, BigDecimal exchangeRate) {

    }

    @Override
    public void updateLocalPrice(CountryBigMac countryBigMac, BigDecimal localPrice) {

    }

    @Override
    public void updateUpdateUrl(CountryBigMac countryBigMac, String updateUrl) {

    }

    @Override
    public void updateWebComponent(CountryBigMac countryBigMac, String webComponent) {

    }

    @Override
    public void save(CountryBigMac countryBigMac) {
        countryBigMacRepository.save(countryBigMac);
    }
}
