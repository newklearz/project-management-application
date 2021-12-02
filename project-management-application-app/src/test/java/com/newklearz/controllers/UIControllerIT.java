package com.newklearz.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;


@SpringBootTest()
@AutoConfigureMockMvc
public class UIControllerIT
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUnauthenticatedEndPoint() throws Exception
    {
        this.mockMvc.perform(get("/users"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    public void testAuthenticatedEndPoint() throws Exception
    {
        mockMvc.perform(get("/users").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("admin:admiN123$".getBytes())))
                .andExpect(status().isOk());
    }
}