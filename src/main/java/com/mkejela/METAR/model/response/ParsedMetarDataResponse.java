package com.mkejela.METAR.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mkejela.METAR.model.ParsedMetar;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ParsedMetarDataResponse {

    @JsonInclude
    private String temperature;

    @JsonInclude
    private String cloudCeiling;

    @JsonInclude
    private String windInformation;

    @JsonInclude
    private String precipitation;

    @JsonInclude
    private String visibility;

    @JsonInclude
    private String windChange;


}
