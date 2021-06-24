package com.smaato.coding.challenge.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EndpointServiceTest {

    @Mock
    ExternalCallService externalCallService;

    @InjectMocks
    EndpointService endpointService;

    @Test
    void test_callEndpoint_was_called_atleast_once() {
        String endpoint = "http://localhost:8080/api/smaato/accept?id=452223&endpoint=http://localhost:9090/api/smaato/get";
        endpointService.callEndpoint(endpoint);
        URI uri = UriComponentsBuilder.fromUriString(endpoint).build().encode().toUri();
        verify(externalCallService, times(1)).getStatusCodeValue(uri);
    }

    @Test
    void test_callEndpoint_return_statuscode() {
        String endpoint = "http://localhost:8080/api/smaato/accept?id=452223&endpoint=http://localhost:9090/api/smaato/get";
        when(externalCallService.getStatusCodeValue(Mockito.any(URI.class))).thenReturn(200);
        URI uri = UriComponentsBuilder.fromUriString(endpoint).build().encode().toUri();
        endpointService.callEndpoint(endpoint);
        verify(externalCallService,times(1)).getStatusCodeValue(uri);
        assertEquals(200, externalCallService.getStatusCodeValue(uri));
    }


}