package com.qaq.base.utils;

import com.qaq.base.exception.UnAuthorizedException;
import com.qaq.base.model.Auth;

public class PermissionChecker {

    public static void check(Auth auth, String projectId, String permissionNeed) {
        if (!auth.havePermission(projectId, permissionNeed)) {
            String msg = String.format("[%s][%s] no permission, need permissionNeed %s of project %s",
                    auth.getToken().getEmail(), auth.getToken().getFullname(),
                    permissionNeed, projectId);
            throw new UnAuthorizedException(msg);
        }
    }

    // check with project id
    public static void checkWithProjectId(Auth auth, String permission, String projectId) {
        check(auth, projectId, permission);
    }

    // check with project id
    public static void checkAdmin(Auth auth, String permissionNeed) {
//        if (!this.authCheckService.isPlatformAdmin(auth.getEmail())) {
//            String msg = String.format("[%s][%s] no permission, need permissionNeed %s ",
//                    auth.getEmail(), auth.getName(), permissionNeed);
        throw new UnAuthorizedException("msg");
    }


    // check with project id
    public static void checkAppAdmin(Auth auth, String permissionNeed, String appName) {
//        if (!authCheckService.requireAppAdmin(appName, auth.getUserid())) {
//            String msg = String.format("[%s][%s] no permission, need permissionNeed %s ",
//                    auth.getEmail(), auth.getName(), permissionNeed);
        throw new UnAuthorizedException("msg");
    }

}
