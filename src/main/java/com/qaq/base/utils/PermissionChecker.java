package com.qaq.base.utils;

import com.qaq.base.enums.PermissionEnum;
import com.qaq.base.exception.UnAuthorizedException;
import com.qaq.base.model.Auth;

public class PermissionChecker {

    private void check(Auth auth, String projectId, PermissionEnum permissionNeed) {
        if (!auth.havePermission(projectId, permissionNeed)) {
            String msg = String.format("[%s][%s] no permission, need permissionNeed %s of project %s",
                    auth.getEmail(), auth.getName(),
                    permissionNeed, projectId);
            throw new UnAuthorizedException(msg);
        }
    }

    // check with project id
    public void checkWithProjectId(Auth auth, PermissionEnum permission, String projectId) {
        check(auth, projectId, permission);
    }
}
