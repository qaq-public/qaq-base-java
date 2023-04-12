package com.qaq.base.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse<T> {

    private boolean success;

    private int code;

    private String message;

    @JsonProperty("data")
    private T data;

    public ApiResponse(int code, String message, T data) {
        this.success = code == 0;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String message, T data) {
        this.success = true;
        this.code = 0;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(T data) {
        this.success = true;
        this.code = 0;
        this.message = "";
        this.data = data;
    }

}
