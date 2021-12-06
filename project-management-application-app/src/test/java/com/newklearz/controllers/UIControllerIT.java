package com.newklearz.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;

import com.newklearz.SpringBootTestEnvironment;



public class UIControllerIT extends SpringBootTestEnvironment
{
    /**
     * Verify if get request is permitted on an authenticated only endpoint access
     */
    @Test
    void testUnauthenticatedEndPoint() throws Exception
    {
        this.mockMvc.perform(get("/users"))
            .andExpect(status().isUnauthorized());
    }

    /**
     * Verify if get request returns an authenticated User Principal after sending correct credentials in header
     */
    @Test
    public void testAuthenticatedEndPoint() throws Exception
    {
        mockMvc.perform(get("/users").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("admin:admiN123$".getBytes())))
                .andExpect(status().isOk());
    }
}