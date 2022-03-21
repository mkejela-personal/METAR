package com.mkejela.METAR.Exception;

public class MetarServiceException extends RuntimeException {

    public static final int NOT_SUBSCRIBED = 0001;

    int code;

    public MetarServiceException(String message, int code){
        super(message);
        this.code = code;

    }


}
