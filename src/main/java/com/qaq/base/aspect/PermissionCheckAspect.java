package com.qaq.base.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.qaq.base.utils.PermissionChecker;
import com.qaq.base.model.Auth;
import com.qaq.base.annotation.CheckPermission;
import com.qaq.base.enums.PermissionEnum;
import com.qaq.base.enums.CheckType;

@Aspect
public class PermissionCheckAspect {

    @Pointcut("@annotation(com.qaq.base.annotation.CheckPermission)")
    public void readCheckerPoint() {
    }

    @Before("readCheckerPoint()")
    public void advice(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        Auth auth = (Auth) request.getAttribute("auth");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CheckPermission checkPermission = method.getAnnotation(CheckPermission.class);
        String val = checkPermission.value();
        String projectId = val.isEmpty() ? null : getIdFromMethodArgs(joinPoint, val);
        String permissionNeed = checkPermission.permission();
        switch (checkPermission.type()) {
            case PROJECT_ID:
                PermissionChecker.checkWithProjectId(auth, permissionNeed, projectId);
                break;
            case ADMIN:
                PermissionChecker.checkAdmin(auth, permissionNeed);
                break;
            case APP_NAME:
                PermissionChecker.checkAppAdmin(auth, permissionNeed, projectId);
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
