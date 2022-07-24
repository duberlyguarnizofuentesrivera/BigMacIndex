package com.mdsperu.bigmacindex.model;

import com.mdsperu.bigmacindex.enums.Country;
import com.mdsperu.bigmacindex.enums.CurrencyCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class CountryBigMac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    BigDecimal localPrice;
    BigDecimal lastExchangeRate;
    @NotNull
    @Enumerated(EnumType.STRING)
    Country country;
    @NotNull
    @Enumerated(EnumType.STRING)
    CurrencyCode currencyCode;
    LocalDateTime lastUpdate;
    String updateUrl;
    String webComponent;
}
