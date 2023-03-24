package com.qaq.base.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CheckType {
    PROJECT_ID("projectId"),
    PLATFORM("platform"),
    ADMIN("admin"),
    APP_NAME("appName");

    private String value;

    CheckType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CheckType fromString(String checkTypeStr) {
        for (var checkType : CheckType.values()) {
            if (checkType.value.equals(checkTypeStr)) {
                return checkType;
            }
        }
        return null;
    }
}
