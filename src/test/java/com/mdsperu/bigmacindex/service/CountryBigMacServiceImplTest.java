package com.mdsperu.bigmacindex.service;

import com.mdsperu.bigmacindex.BigMacIndexApplication;
import com.mdsperu.bigmacindex.enums.Country;
import com.mdsperu.bigmacindex.enums.CurrencyCode;
import com.mdsperu.bigmacindex.model.CountryBigMac;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationTest.properties")
class CountryBigMacServiceImplTest {
    @Autowired
    private CountryBigMacServiceImpl countryBigMacServiceImpl;
    @Autowired
    private MockMvc mvc;
    private CountryBigMac countryBigMac;

    @BeforeEach
    void setUp() {
        countryBigMac = new CountryBigMac();
        countryBigMac.setCountry(Country.ARGENTINA);
        countryBigMac.setCurrencyCode(CurrencyCode.ARS);
        countryBigMac.setLastExchangeRate(new BigDecimal(1.0));
        countryBigMac.setLocalPrice(new BigDecimal(1.0));
        countryBigMac.setUpdateUrl("https://www.google.com");
        countryBigMac.setWebComponent("<div id='price'>");
        countryBigMacServiceImpl.save(countryBigMac);
    }

    @Test
    void getByCountryNameReturnsRightCountry() {
        CountryBigMac countryBigMac2 = countryBigMacServiceImpl.getCountryBigMacByCountryName(Country.ARGENTINA);
        assertEquals(Country.ARGENTINA, countryBigMac2.getCountry());
    }

    @Test
    void getCountryBigMacByCountryName() {
    }

    @Test
    void getCountryBigMacByCurrencyCode() {
    }

    @Test
    void updateLastExchangeRate() {
    }

    @Test
    void updateLocalPrice() {
    }

    @Test
    void updateUpdateUrl() {
    }

    @Test
    void updateWebComponent() {
    }
}