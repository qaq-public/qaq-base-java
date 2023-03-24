package com.qaq.base.model.uniauth;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaq.base.enums.PermissionEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResult {
    private boolean platform;
    private List<String> app;
    private Map<String, List<String>> project;
    private String userAccessToken;
}
