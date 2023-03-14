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

}
