package com.mkejela.METAR.service;

import com.mkejela.METAR.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Override
    public Boolean isSubscribed(String icaoCode) {

        return subscriptionRepository.findSubscriptionByIcaoCode(icaoCode)!=null;
    }
}
