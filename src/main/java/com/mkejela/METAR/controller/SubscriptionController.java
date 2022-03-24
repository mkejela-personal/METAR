package com.mkejela.METAR.controller;

import com.mkejela.METAR.model.request.SubsriptionRequest;
import com.mkejela.METAR.model.response.CommonResponse;
import com.mkejela.METAR.model.response.SubscriptionResponse;
import com.mkejela.METAR.service.SubscriptionService;
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

}
