package com.mkejela.METAR.service;

import com.mkejela.METAR.Exception.MetarServiceException;
import com.mkejela.METAR.model.Metar;
import com.mkejela.METAR.model.response.WeatherServiceResponse;
import com.mkejela.METAR.repository.MetarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetarWeatherServiceImpl implements MetarWeatherService {

    @Autowired
    private MetarRepository metarRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public WeatherServiceResponse addMetar(String data) throws MetarServiceException {

        WeatherServiceResponse response = new WeatherServiceResponse();
        Metar metar = new Metar();

        String icaoCode="";

        String[] splittedData=data.split("\\s+");

        if(splittedData.length!=1){
            icaoCode=splittedData[1];
        }

        if(!validateRequest(icaoCode)){
           throw new MetarServiceException("Airport not subscribed", MetarServiceException.NOT_SUBSCRIBED);
        }

        response.setMessage("success");
        response.setIcao(icaoCode);
        response.setData(data);
        metar.setData(data);
        metar.setAirportIcaoCode(icaoCode);

        metarRepository.save(metar);

        return response;
    }

    private Boolean validateRequest(String icaoCode){

        return icaoCode!= null && subscriptionService.isSubscribed(icaoCode);

    }
}
