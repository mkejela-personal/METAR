package com.mkejela.METAR.model.request;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpecificMetarRequest {

    @JsonInclude
    List<String> requestedData;
}
