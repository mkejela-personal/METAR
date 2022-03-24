package com.mkejela.METAR.controller;

import com.mkejela.METAR.model.request.SubsriptionRequest;
import com.mkejela.METAR.model.request.UnsubscribeRequest;
import com.mkejela.METAR.model.response.CommonResponse;
import com.mkejela.METAR.model.response.SubscriptionResponse;
import com.mkejela.METAR.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("subscriptions")
    public CommonResponse subscribeAirport(@RequestBody SubsriptionRequest request){

        return subscriptionService.subscribeAirport(request.getIcaoCode());
    }

    @GetMapping("subscriptions")
    public SubscriptionResponse getAllSubscribedAirports(){

        return subscriptionService.getAllSubscribedAirports();
    }

    @PutMapping("subscriptions/{airport}")
    public ResponseEntity<String> unsubscribe(@PathVariable("airport") String airport, @RequestBody UnsubscribeRequest request){

        try {
           String message =  subscriptionService.unsubscribeAirport(airport, request.getIsActive());
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("failed to unsubscribe, make sure the airport has been subscribed before", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("subscriptions/active")
    public SubscriptionResponse getAllActiveSubscriptions(@RequestBody UnsubscribeRequest request){
        return subscriptionService.getAllActiveSubscriptions(request.getIsActive());
    }
}
