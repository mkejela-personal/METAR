package com.mkejela.METAR.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mkejela.METAR.model.ParsedMetar;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class WeatherServiceResponse extends CommonResponse {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String data;
    ParsedMetar parsedData;
}
