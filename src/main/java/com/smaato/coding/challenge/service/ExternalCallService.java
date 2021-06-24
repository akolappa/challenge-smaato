package com.smaato.coding.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class ExternalCallService {

    @Autowired
    private RestTemplate restTemplate;

    public int getStatusCodeValue(URI uri) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, String.class).getStatusCodeValue();
    }
}
