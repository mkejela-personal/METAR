package com.mkejela.METAR.repository;

import com.mkejela.METAR.model.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

    Subscription findSubscriptionByIcaoCode(String icaoCode);

    @Query(value = "select * from subscription", nativeQuery = true)
    List<Subscription> getAllSubscribedAirports();
}
