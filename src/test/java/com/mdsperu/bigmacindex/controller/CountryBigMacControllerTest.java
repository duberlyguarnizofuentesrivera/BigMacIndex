package com.mdsperu.bigmacindex.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationTest.properties")
class CountryBigMacControllerTest {
    @Autowired
    CountryBigMacController countryBigMacController;

    @Test
    void getUpdatedRatesFromApiTest() {
        String result = countryBigMacController.getUpdatedRatesFromApi();
        assertNotNull(result);
        assertTrue(result.contains("rates"));
        assertTrue(result.contains("USD"));
    }

}