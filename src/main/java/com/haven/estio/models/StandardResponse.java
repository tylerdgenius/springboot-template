package com.haven.estio.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class StandardResponse<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;

    public StandardResponse(int code, String message, T data) {
        this.code = code;
        this.success = code > 400;
        this.message = message;
        this.data = data;
    }
}
