package com.qaq.base.intercepter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qaq.base.exception.UnAuthorizedException;
import com.qaq.base.model.Auth;
import com.qaq.base.model.login.Token;
import com.qaq.base.model.uniauth.AuthResult;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private static final Base64.Decoder base64UrlDecoder = Base64.getUrlDecoder();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        var tokenStr = request.getHeader("X-Gateway-Token");
        var permissionStr = request.getHeader("X-Gateway-Permission");
        var auth = getAuth(tokenStr, permissionStr);
        request.setAttribute("auth", auth);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
    }

    private Auth getAuth(@NonNull String tokenStr, @NonNull String permissionStr) {
        var auth = new Auth();
        var objectMapper = new ObjectMapper();
        // Auth 信息一定要有
        if (!tokenStr.trim().isEmpty()) {
            try {
                var token = objectMapper.readValue(base64UrlDecoder.decode(tokenStr), Token.class);
                auth.setToken(token);
            } catch (Exception e) {
                log.error("decode the X-Gateway-Token header failed. string: {}, ex:{}", tokenStr, e);
                e.printStackTrace();
                throw new UnAuthorizedException("the auth get from gateway header decode failed");
            }
        } else {
            log.error("can not get auth info from request header");
            throw new UnAuthorizedException("the auth get from gateway header decode failed");
        }
        if (!permissionStr.trim().isEmpty()) {
            try {
                var authResult = objectMapper.readValue(base64UrlDecoder.decode(permissionStr),
                        AuthResult.class);
                auth.setAuthResult(authResult);
            } catch (Exception e) {
                log.error("decode the X-Gateway-Permission header failed. string: {}, ex:{}", permissionStr, e);
                e.printStackTrace();
                throw new UnAuthorizedException("the auth get from gateway header decode failed");
            }
        } else {
            log.warn("the value of permission header is empty");
            throw new UnAuthorizedException("the auth get from gateway header decode failed");
        }
        return auth;
    }
}
