package com.mdsperu.bigmacindex.repository;

import com.mdsperu.bigmacindex.enums.Country;
import com.mdsperu.bigmacindex.model.CountryBigMac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryBigMacRepository extends JpaRepository<CountryBigMac, Integer> {
    List<CountryBigMac> findFirstByCountry(Country country);
}
