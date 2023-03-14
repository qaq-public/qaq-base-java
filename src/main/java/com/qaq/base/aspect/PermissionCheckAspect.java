package com.qaq.base.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.qaq.base.utils.PermissionChecker;
import com.qaq.base.model.Auth;
import com.qaq.base.annotation.CheckPermission;
import com.qaq.base.enums.PermissionEnum;
import com.qaq.base.enums.CheckType;

@Aspect
public class PermissionCheckAspect {

    private PermissionChecker permissionChecker = new PermissionChecker();

    @Pointcut("@annotation(checkPermission) && execution(* *(..))")
    public void readCheckerPoint(CheckPermission checkPermission) {
    }

    @Before("readCheckerPoint(checkPermission)")
    public void advice(JoinPoint joinPoint, CheckPermission checkPermission) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        Auth auth = (Auth) request.getAttribute("auth");
        String val = checkPermission.value();
        String id = val.isEmpty() ? null : getIdFromMethodArgs(joinPoint, val);
        PermissionEnum permissionNeed = checkPermission.permission();
        CheckType checkType = checkPermission.type();
        switch (checkType) {
            case PROJECT_ID:
                permissionChecker.checkWithProjectId(auth, permissionNeed, id);
                break;
            default:
                throw new RuntimeException("not define this checkType!!!");
        }
    }

    private String getIdFromMethodArgs(JoinPoint joinPoint, String val) {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] paramNames = codeSignature.getParameterNames();
        int idx = Arrays.asList(paramNames).indexOf(val);
        if (idx == -1) {
            throw new RuntimeException("the key not find in method parameters");
        }
        return (String) joinPoint.getArgs()[idx];
    }

}
