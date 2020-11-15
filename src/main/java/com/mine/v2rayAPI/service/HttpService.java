package com.mine.v2rayAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class HttpService {
    @Autowired
    private RestTemplate restTemplate;

    public <T,R> ResponseEntity<R> exechange(String url,HttpMethod method,HttpHeaders headers, T body, Class<R> responseType){
        if(Objects.isNull(headers)){
            headers = new HttpHeaders();
        }
        headers.add("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(url, method, requestEntity,responseType);
    }
}
