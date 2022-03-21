package com.mkejela.METAR.repository;

import com.mkejela.METAR.model.Metar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MetarRepository extends CrudRepository<Metar, Integer> {

    Metar getMetarByAirportIcaoCode(String icaoCode);
}
