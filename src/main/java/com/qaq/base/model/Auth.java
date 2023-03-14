package com.qaq.base.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qaq.base.enums.PermissionEnum;

import lombok.Data;

@Data
public class Auth {

    private String email;
    private String name;
    private String userid;
    private String openid;
    private String avatar;
    private List<PermissionEnum> all = new ArrayList<>();;
    private Map<String, List<PermissionEnum>> project = new HashMap<>();

    public boolean havePermission(String projectId, PermissionEnum permission) {
        return (all.contains(permission) || project.getOrDefault(projectId, new ArrayList<>()).contains(permission));
    }
}
