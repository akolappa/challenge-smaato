package com.smaato.coding.challenge.controller;

import com.smaato.coding.challenge.service.EndpointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiController {

    @Autowired
    private EndpointService endpointService;

    @GetMapping("/api/smaato/accept")
    public String accept(@RequestParam Long id,
                        @RequestParam(required = false) String endpoint){
        try{
            endpointService.addIdToSet(id);
            if(endpoint != null && !endpoint.isEmpty()) {
                endpointService.callEndpoint(endpoint);
            }
            return "ok";
        }catch (Exception e){
            return "failed";
        }
    }

}
