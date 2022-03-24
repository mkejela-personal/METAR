package com.mkejela.METAR.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.websocket.server.ServerEndpoint;

@Setter
@Getter
public class UnsubscribeRequest {

    @JsonInclude
    Boolean isActive;
}
