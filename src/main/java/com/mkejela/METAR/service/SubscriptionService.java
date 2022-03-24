package com.mkejela.METAR.service;

import com.mkejela.METAR.model.Subscription;
import com.mkejela.METAR.model.response.CommonResponse;
import com.mkejela.METAR.model.response.SubscriptionResponse;

import java.util.List;

public interface SubscriptionService {

    Boolean isSubscribed(String icaoCode);

    CommonResponse subscribeAirport(String icaoCode);

    SubscriptionResponse getAllSubscribedAirports();

}
