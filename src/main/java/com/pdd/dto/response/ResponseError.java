package com.pdd.dto.response;


import org.springframework.http.HttpStatusCode;

public class ResponseError extends ResponseData<Integer> {
    public ResponseError(HttpStatusCode code, String message) {
        super(code.value(), message);
    }
}
