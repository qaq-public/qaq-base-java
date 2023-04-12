package com.qaq.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class HttpUtil {

    static final private RestTemplate restTemplate = new RestTemplate();

    static public <T, B> T exchange(String url, HttpMethod method, B body, Class<T> responseType) {
        log.info("url: " + url);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<B> requestEntity = new HttpEntity<>(body, headers);
        log.debug("entity===>{}",requestEntity);
        ResponseEntity<T> resultEntity = restTemplate.exchange(url, method, requestEntity, responseType);
        return resultEntity.getBody();
    }

    static public <T, B> T exchange(String url, HttpMethod method, B body, Class<T> responseType, String authorization) {
        log.info("url: " + url);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authorization);
        HttpEntity<B> requestEntity = new HttpEntity<>(body, headers);
        log.debug("entity===>{}",requestEntity);
        ResponseEntity<T> resultEntity = restTemplate.exchange(url, method, requestEntity, responseType);
        return resultEntity.getBody();
    }

    static public <T, B> T exchange(String url, HttpMethod method, B body, ParameterizedTypeReference<T> responseType, String authorization) {
        log.info("url: " + url);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authorization);
        HttpEntity<B> requestEntity = new HttpEntity<>(body, headers);
        log.debug("entity===>{}",requestEntity);
        ResponseEntity<T> resultEntity = restTemplate.exchange(url, method, requestEntity, responseType);
        return resultEntity.getBody();
    }

}
