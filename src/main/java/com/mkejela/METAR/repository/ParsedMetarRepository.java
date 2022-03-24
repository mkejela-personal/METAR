package com.mkejela.METAR.repository;

import com.mkejela.METAR.model.ParsedMetar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParsedMetarRepository extends CrudRepository<ParsedMetar, Integer> {

    ParsedMetar getParsedMetarByAirPortCode(String airportCode);

}
