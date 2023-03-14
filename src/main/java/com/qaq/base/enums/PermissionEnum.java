package com.qaq.base.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PermissionEnum {
    DELETE("delete"),
    DELETEALL("deleteAll"),
    READ("read"),
    READALL("deleteAll"),
    WRITE("write"),
    WRITEALL("writeAll"),
    ;

    private String value;

    PermissionEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PermissionEnum fromString(String permissionStr) {
        System.out.println(permissionStr);
        for (PermissionEnum permission : PermissionEnum.values()) {
            if (permission.value.equals(permissionStr)) {
                return permission;
            }
        }
        return null;
    }
}