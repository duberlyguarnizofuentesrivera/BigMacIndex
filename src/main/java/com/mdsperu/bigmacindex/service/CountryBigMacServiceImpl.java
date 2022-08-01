package com.mdsperu.bigmacindex.service;

import com.mdsperu.bigmacindex.enums.CurrencyCode;
import com.mdsperu.bigmacindex.model.CountryBigMac;
import com.mdsperu.bigmacindex.repository.CountryBigMacRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class CountryBigMacServiceImpl implements CountryBigMacService {
    private static final Logger log = getLogger(CountryBigMacServiceImpl.class);

    private CountryBigMacRepository countryBigMacRepository;

    @Autowired
    public void setCountryBigMacRepository(CountryBigMacRepository countryBigMacRepository) {
        this.countryBigMacRepository = countryBigMacRepository;
    }

    @Override
    public CountryBigMac getCountryBigMacByCurrencyCode(CurrencyCode currencyCode) {
        List<CountryBigMac> countryBigMacList = countryBigMacRepository.findFirstByCurrencyCode(currencyCode);
        return countryBigMacList.get(0);
    }

    @Override
    public void updateLastExchangeRate(CountryBigMac countryBigMac, BigDecimal exchangeRate) {
        countryBigMac.setLastExchangeRate(exchangeRate);
        countryBigMac.setLastExchangeRateUpdated(LocalDateTime.now());
        countryBigMacRepository.save(countryBigMac);
    }

    @Override
    public void updateLocalPrice(CountryBigMac countryBigMac, BigDecimal localPrice) {
        countryBigMac.setLocalPrice(localPrice);
        countryBigMac.setLastPriceUpdated(LocalDateTime.now());
        countryBigMacRepository.save(countryBigMac);
        String countryName = countryBigMac.getCurrencyCode().getName();
        log.info("updating LOCAL_PRICE for country: {}", countryName);
    }

    @Override
    public void updateUpdateUrl(CountryBigMac countryBigMac, String updateUrl) {
        countryBigMac.setUpdateUrl(updateUrl);
        countryBigMacRepository.save(countryBigMac);
        String countryName = countryBigMac.getCurrencyCode().name();
        log.info("updating UPDATE_URL for country: {}", countryName);
    }

    @Override
    public void updateWebComponent(CountryBigMac countryBigMac, String webComponent) {
        countryBigMac.setWebComponent(webComponent);
        countryBigMacRepository.save(countryBigMac);
        String countryName = countryBigMac.getCurrencyCode().name();
        log.info("updating WEB_COMPONENT for country: {}", countryName);
    }

    @Override
    public void save(CountryBigMac countryBigMac) {
        countryBigMac.setLastUpdate(LocalDateTime.now());
        countryBigMacRepository.save(countryBigMac);
        String countryName = countryBigMac.getCurrencyCode().name();
        log.info("Saving changed data for country: {}", countryName);
    }

    @Override
    public boolean existsByCurrencyCode(CurrencyCode currencyCode) {
        return countryBigMacRepository.existsByCurrencyCode(currencyCode);
    }

    @Override
    public List<CountryBigMac> getAllRates() {
        return countryBigMacRepository.findAll();
    }

}
