package com.mkejela.METAR.service;

import com.mkejela.METAR.Exception.MetarServiceException;
import com.mkejela.METAR.model.response.WeatherServiceResponse;

public interface MetarWeatherService {

    WeatherServiceResponse addMetar(String data) throws MetarServiceException;
    MetarWeatherInfoResponse getMetarData();

}
