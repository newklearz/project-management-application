package com.newklearz.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;

import com.newklearz.SpringBootTestEnvironment;



public class UIControllerIT extends SpringBootTestEnvironment
{
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