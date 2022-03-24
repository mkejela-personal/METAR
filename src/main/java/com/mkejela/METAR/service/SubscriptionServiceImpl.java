package com.mkejela.METAR.service;

import com.mkejela.METAR.model.Subscription;
import com.mkejela.METAR.model.response.CommonResponse;
import com.mkejela.METAR.model.response.SubscriptionResponse;
import com.mkejela.METAR.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Override
    public CommonResponse subscribeAirport(String icaoCode) {

        CommonResponse response = new CommonResponse();

        if(icaoCode ==null || icaoCode.isEmpty()){
            response.setMessage("Failed to subscribe airport.");
            return response;
        }

        Subscription subscription = subscriptionRepository.getSubscriptionByIcaoCode(icaoCode); //make sure the airport hasn't subscribed before
        if(subscription!=null){
            response.setMessage("Airport already subscribed");
            return response;
        }

        subscription = new Subscription();
        subscription.setIcaoCode(icaoCode);
        subscription.setIsActive(Boolean.TRUE);

        try{
            subscriptionRepository.save(subscription);
        } catch (Exception e){
            response.setMessage(e.getMessage()); //Just to know why it failed
            return response;
        }

        response.setIcaoCode(icaoCode);
        response.setMessage("Success");

        return response;
    }

    @Override
    public SubscriptionResponse getAllSubscribedAirports() {

        SubscriptionResponse response = new SubscriptionResponse();
        List<Subscription> subscriptionList;
        try {
            subscriptionList = subscriptionRepository.getAllSubscribedAirports();
        } catch (Exception e){
            response.setMessage(e.getMessage()); //just to have some idea of what has happened
            return response;
        }

        if(subscriptionList==null){
            response.setMessage("no airports subscribed");
            return response;
        }

        List<String> subscribedAirports=subscriptionList.stream().map(Subscription::getIcaoCode).collect(Collectors.toList());

        response.setSubscribedAirports(subscribedAirports);


        return response;
    }

    @Override
    public String unsubscribeAirport(String airportCode, Boolean active) {

        String message="";

        Subscription airport=subscriptionRepository.getSubscriptionByIcaoCode(airportCode);

      if (isSubscribed(airportCode)){
          airport.setIsActive
                  (active);
          subscriptionRepository.save(airport);
      }

      else
          message="airport is not subscribed or is already deactive";
        return message;
    }

    @Override
    public SubscriptionResponse getAllActiveSubscriptions(Boolean isActive) {

        SubscriptionResponse response = new SubscriptionResponse();

        List<Subscription> subscriptionList=subscriptionRepository.getAllByIsActive(isActive);

        response.setSubscribedAirports(subscriptionList.stream().map(Subscription::getIcaoCode).collect(Collectors.toList()));

        return response;
    }

    @Override
    public Boolean isSubscribed(String icaoCode){
        return subscriptionRepository.findSubscriptionByIcaoCode(icaoCode)!=null
                && Boolean.TRUE.equals(subscriptionRepository.getSubscriptionByIcaoCode(icaoCode).getIsActive());
    }
}
