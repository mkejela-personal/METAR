package com.mkejela.METAR.repository;

import com.mkejela.METAR.model.Metar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


@Repository
public interface MetarRepository extends CrudRepository<Metar, Integer>{

    Metar getMetarByAirportIcaoCode(String icaoCode);

    @Query(value = "select * from metar order by id desc limit 1", nativeQuery = true)
    Metar getLastInsertedMetar();
}
