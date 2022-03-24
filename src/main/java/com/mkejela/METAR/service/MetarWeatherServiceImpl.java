package com.mkejela.METAR.service;

import com.mkejela.METAR.Exception.MetarServiceException;
import com.mkejela.METAR.model.MetaDataEnum;
import com.mkejela.METAR.model.Metar;
import com.mkejela.METAR.model.ParsedMetar;
import com.mkejela.METAR.model.response.ParsedMetarDataResponse;
import com.mkejela.METAR.model.response.WeatherServiceResponse;
import com.mkejela.METAR.repository.MetarRepository;
import com.mkejela.METAR.repository.ParsedMetarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.beans.IntrospectionException;
import java.util.*;


@Service
public class MetarWeatherServiceImpl implements MetarWeatherService {

    @Autowired
    private MetarRepository metarRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ParsedMetarRepository parsedMetarRepository;

    private final int metarDateStringLength=6;

    public MetarWeatherServiceImpl(MetarRepository metarRepository, SubscriptionService subscriptionService) {
        this.metarRepository = metarRepository;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public WeatherServiceResponse addMetar(String airport, String data) throws MetarServiceException {

        Metar metar = new Metar();


        String[] splittedData = data.split("\\s+");
        WeatherServiceResponse response = new WeatherServiceResponse();


        if (!validateRequest(airport)) {
            response.setMessage("airport not subscribed");
            response.setIcaoCode(airport);
            return response;
        }

        response.setMessage("success");
        response.setIcaoCode(airport);
        response.setData(data);
        response.setParsedData(parseMetarData(airport, data));
        metar.setData(data);
        metar.setAirportIcaoCode(airport);


        ParsedMetar parsedMetar = parseMetarData(airport, data);

        response.setParsedData(parsedMetar);

        metarRepository.save(metar);

        parsedMetarRepository.save(parsedMetar);

        return response;
    }

    @Override
    public WeatherServiceResponse getMetarData(String airport) {
        Metar metar = metarRepository.getLastInsertedMetar(airport);
        WeatherServiceResponse response = new WeatherServiceResponse();
        response.setMessage("Success");
        response.setData(metar.getData());
        return response;
    }

    @Override
    public ParsedMetarDataResponse getSpecificMetarDataForAirport(List<String> requiredData, String airport) {

        ParsedMetar parsedMetar = parsedMetarRepository.getParsedMetarByAirPortCode(airport);
        ParsedMetarDataResponse response = new ParsedMetarDataResponse();

        List<MetaDataEnum> metarDataEnum=new ArrayList<>(Arrays.asList(MetaDataEnum.TEMPERATURE, MetaDataEnum.CLOUD_CEILING,
                MetaDataEnum.WIND_CHANGE, MetaDataEnum.RUNWAY_VISIBILITY, MetaDataEnum.WIND_INFORMATION, MetaDataEnum.PRECIPITATION));
       if(parsedMetar!=null){
           if(requiredData.contains(metarDataEnum.get(0).toString())){
               response.setTemperature(parsedMetar.getTemperature());
           }
           if(requiredData.contains(metarDataEnum.get(1).toString())){
               response.setCloudCeiling(parsedMetar.getCloudCeiling());
           }
           if(requiredData.contains(metarDataEnum.get(2).toString())){
               response.setWindChange(parsedMetar.getWindDirectionVariation());
           }

           if(requiredData.contains(metarDataEnum.get(3).toString())){
               response.setVisibility(parsedMetar.getVisibility());
           }
           if(requiredData.contains(metarDataEnum.get(4).toString())){
               response.setWindInformation(parsedMetar.getWindInformation());
           }
           if (requiredData.contains(metarDataEnum.get(5).toString())){
               response.setPrecipitation(parsedMetar.getPrecipitationIntensity());
           }
       }
        return response;
    }

    private Boolean validateRequest(String icaoCode) {

        return icaoCode != null && subscriptionService.isSubscribed(icaoCode);

    }

    private ParsedMetar parseMetarData(String airPort, String data) {
        Map<Integer, String> parsed = new HashMap<>();

        List<Map<Integer, String>> listOfParsedDetails = new ArrayList<>();
        List<String> parsedDate = new ArrayList<>(Arrays.asList(data.split("\\s+")));

        for (int i = 0; i < parsedDate.size(); i++) {
            parsed.put(i, parsedDate.get(i));
        }

        ParsedMetar parsedMetar=new ParsedMetar();
        if(!parsed.isEmpty()){

            if(parsed.entrySet().contains("AUTO")){
                if(parsed.entrySet().contains("METAR")){
                    parsedMetar.setWindInformation(parsed.get(4));
                }
                else
                    parsedMetar.setWindInformation(parsed.get(3));
            }
            if(parsed.entrySet().contains("METAR")){
                parsedMetar.setAirPortCode(airPort);
                parsedMetar.setWindInformation(parsed.get(3));
                parsedMetar.setCloudCeiling(parsed.get(9));
                parsedMetar.setTimeOfObservation(parsed.get(2));
                parsedMetar.setVisibility(parsed.get(5));
                parsedMetar.setPrecipitationIntensity(parsed.get(8));
                parsedMetar.setRunwayVisualRange(parsed.get(6));
                parsedMetar.setWindDirectionVariation(parsed.get(5));
            }
            parsedMetar.setAirPortCode(airPort);
            parsedMetar.setWindInformation(parsed.get(2));
            parsedMetar.setCloudCeiling(parsed.get(8));
            parsedMetar.setTimeOfObservation(parsed.get(1));
            parsedMetar.setVisibility(parsed.get(4));
            parsedMetar.setPrecipitationIntensity(parsed.get(7));
            parsedMetar.setRunwayVisualRange(parsed.get(5));
            parsedMetar.setWindDirectionVariation(parsed.get(3));

        }

        return parsedMetar;
    }
}
