package com.mkejela.METAR.repository;

import com.mkejela.METAR.model.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    Subscription findSubscriptionByIcaoCode(String icaoCode);
}
