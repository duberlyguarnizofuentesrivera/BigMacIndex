package com.mdsperu.bigmacindex.controller;

import com.mdsperu.bigmacindex.enums.CountryCode;
import com.mdsperu.bigmacindex.model.CountryBigMac;
import com.mdsperu.bigmacindex.service.CountryBigMacServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/")
public class IndexController {
    private final Logger log = getLogger(IndexController.class.getName());
    @Autowired
    CountryBigMacServiceImpl countryBigMacServiceImpl;

    @RequestMapping("/")
    public String index(Model model) {

        List<CountryBigMac> currencyList = countryBigMacServiceImpl.getAllRates();
        List<CountryCode> countryCodeList = List.of(CountryCode.values());
        model.addAttribute("currencyList", currencyList);
        model.addAttribute("countryCodeList", countryCodeList);
        return "index";
    }
}
