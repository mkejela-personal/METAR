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

    public MetarWeatherServiceImpl(MetarRepository metarRepository, SubscriptionService subscriptionService) {
        this.metarRepository = metarRepository;
        this.subscriptionService = subscriptionService;
    }
    @Override
    public WeatherServiceResponse addMetar(String data) throws MetarServiceException {
        
        Metar metar = new Metar();

        String icaoCode="";

        String[] splittedData=data.split("\\s+");
        WeatherServiceResponse response = new WeatherServiceResponse();

        if (data.startsWith("METAR")){
        if(splittedData.length!=1){
            icaoCode=splittedData[1];
            }
        }
        else
            icaoCode = splittedData[0];


        if(!validateRequest(icaoCode)){
            response.setMessage("airport not subscribed");
            response.setIcaoCode(icaoCode);
            return response;
        }

        response.setMessage("success");
        response.setIcaoCode(icaoCode);
        response.setData(data);
        metar.setData(data);
        metar.setAirportIcaoCode(icaoCode);

        metarRepository.save(metar);

        return response;
    }

    @Override
    public WeatherServiceResponse getMetarData() {
        Metar metar=metarRepository.getLastInsertedMetar();
        WeatherServiceResponse response = new WeatherServiceResponse();
        response.setMessage("Success");
        response.setData(metar.getData());
        return response;
    }

    private Boolean validateRequest(String icaoCode){

        return icaoCode!=null && subscriptionService.isSubscribed(icaoCode);

    }
}
