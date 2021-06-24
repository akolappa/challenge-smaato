package com.smaato.coding.challenge.controller;

import com.smaato.coding.challenge.service.EndpointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private EndpointService endpointService;

    @Test
    void accept_id_only() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/smaato/accept")
                                                                .queryParam("id","123");

        mvc.perform(requestBuilder).andExpect(content().string("ok"));

    }

    @Test
    void not_giving_id_as_queryparam() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/smaato/accept");

        mvc.perform(requestBuilder).andExpect(status().isBadRequest());

    }

    @Test
    void accept_id_and_endpoint() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/smaato/accept")
                .queryParam("id","123")
                .queryParam("endpoint","http://localhost:8080/api/smaato/accept?id=452223&endpoint=http://localhost:9090/api/smaato/get");

        mvc.perform(requestBuilder).andExpect(content().string("ok"));

    }
}