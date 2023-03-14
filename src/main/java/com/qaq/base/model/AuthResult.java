package com.qaq.base.model;

import java.util.List;
import java.util.Map;

import com.qaq.base.enums.PermissionEnum;

import lombok.Data;

@Data
public class AuthResult {
    private List<PermissionEnum> app;
    private Map<String, List<PermissionEnum>> project;
    private String userAccessToken;
}
