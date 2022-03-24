package com.mkejela.METAR.service;

import com.mkejela.METAR.Exception.MetarServiceException;
import com.mkejela.METAR.model.response.ParsedMetarDataResponse;
import com.mkejela.METAR.model.response.WeatherServiceResponse;

import java.util.List;

public interface MetarWeatherService {

    WeatherServiceResponse addMetar(String airport, String data) throws MetarServiceException;
    WeatherServiceResponse getMetarData(String airPort);

    ParsedMetarDataResponse getSpecificMetarDataForAirport(List<String> requiredData, String airport);

}
