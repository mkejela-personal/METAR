package com.mkejela.METAR.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class AddMetarDataRequest {

    @JsonInclude
    private String icaoCode;

    @JsonInclude
    private String metarData;
}
