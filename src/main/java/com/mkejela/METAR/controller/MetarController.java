package com.mkejela.METAR.controller;


import com.mkejela.METAR.Exception.MetarServiceException;
import com.mkejela.METAR.model.request.AddMetarDataRequest;
import com.mkejela.METAR.model.request.SpecificMetarRequest;
import com.mkejela.METAR.model.response.ParsedMetarDataResponse;
import com.mkejela.METAR.model.response.WeatherServiceResponse;
import com.mkejela.METAR.service.MetarWeatherService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;


@RestController
@RequestMapping("airport/")
public class MetarController {
    @Autowired
    private MetarWeatherService metarWeatherService;

    @PostMapping("{airport}/METAR")
    public WeatherServiceResponse addMetarData(@PathVariable("airport") String airport, @RequestBody AddMetarDataRequest addMetarDataRequest){
        WeatherServiceResponse response = new WeatherServiceResponse();
        try {
          response = metarWeatherService.addMetar(airport, addMetarDataRequest.getMetarData());
       }
       catch (MetarServiceException metarServiceException){
            response.setMessage(metarServiceException.getMessage());
            return response;
       }

        return response;
    }

    @GetMapping("{airport}/METAR")
    public WeatherServiceResponse getMetar(@PathVariable("airport") String airport){
        return metarWeatherService.getMetarData(airport);
    }

    @GetMapping("{airport}/METAR/specific")
    public ParsedMetarDataResponse getSpecificMetarData(@PathVariable("airport") String airport, SpecificMetarRequest request){

        return metarWeatherService.getSpecificMetarDataForAirport(request.getRequestedData(), airport);
    }
}
