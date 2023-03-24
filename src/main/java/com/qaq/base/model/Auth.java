package com.qaq.base.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qaq.base.enums.PermissionEnum;

import com.qaq.base.model.login.Token;
import com.qaq.base.model.uniauth.AuthResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Auth {

    private Token token;
    private AuthResult authResult;

    public boolean havePermission(String projectId, String permission) {
        return (authResult.getApp().contains(permission) || authResult.getProject().getOrDefault(projectId, new ArrayList<>()).contains(permission));
    }
}
