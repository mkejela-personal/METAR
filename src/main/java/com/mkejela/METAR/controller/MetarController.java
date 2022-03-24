package com.mkejela.METAR.controller;


import com.mkejela.METAR.Exception.MetarServiceException;
import com.mkejela.METAR.model.request.AddMetarDataRequest;
import com.mkejela.METAR.model.response.WeatherServiceResponse;
import com.mkejela.METAR.service.MetarWeatherService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;


@RestController
@RequestMapping("airport/")
public class MetarController {
    @Autowired
    private MetarWeatherService metarWeatherService;

    @PostMapping("addMetar")
    public WeatherServiceResponse addMetarData(@RequestBody AddMetarDataRequest addMetarDataRequest){
        WeatherServiceResponse response = new WeatherServiceResponse();
        try {
          response = metarWeatherService.addMetar(addMetarDataRequest.getMetarData());
       }
       catch (MetarServiceException metarServiceException){
            response.setMessage(metarServiceException.getMessage());
            return response;
       }

        return response;
    }

    @GetMapping("getMetar")
    public WeatherServiceResponse getMetar(){
        return metarWeatherService.getMetarData();
    }
}
