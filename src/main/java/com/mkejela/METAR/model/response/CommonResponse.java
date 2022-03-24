package com.mkejela.METAR.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CommonResponse {

    @JsonInclude
    private String icaoCode;

    @JsonInclude
    private String message;
}
