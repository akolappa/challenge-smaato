package com.smaato.coding.challenge.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Service
@EnableScheduling
public class EndpointService {

    @Autowired
    ExternalCallService externalCallService;

    private Set<Long> idSet = new CopyOnWriteArraySet<>();

    @Async
    public void callEndpoint(String endpoint){
        URI uri = UriComponentsBuilder.fromUriString(endpoint).build().encode().toUri();

        int httpStatusCode = externalCallService.getStatusCodeValue(uri);

        log.info("Status code from the endpoint {} is {}",endpoint,httpStatusCode);
    }



    public void addIdToSet(Long id) throws Exception{
        idSet.add(id);
    }

    @Scheduled(cron = "0 * * ? * *")
    public void logData(){
        log.info("No of unique Ids are -->"+idSet.size());
        idSet.clear();
    }
}
