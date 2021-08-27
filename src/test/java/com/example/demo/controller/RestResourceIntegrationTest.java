package com.example.demo.controller;

import com.example.demo.model.ScanContentResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestResource.class)
class RestResourceIntegrationTest {


    @Autowired
    private MockMvc mvc;

    @Test
    void getTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/");
        MvcResult result = mvc.perform(request).andReturn();
        assertEquals("Hi Visitor, check the API documentation for profanity check !", result.getResponse().getContentAsString());
    }



    @Test
    public void censorFilewithCurseWord() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/censorContent")
                .content("Hi How are you. Test word : FUCK");
        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("Hi How are you. Test word : ****", result.getResponse().getContentAsString());
    }

    @Test
    public void censorFilewithoutCurseWord() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/censorContent")
                .content("Hi How are you. Test word : APPLE");
        MvcResult result = mvc.perform(request).andReturn();

        assertEquals("Hi How are you. Test word : APPLE", result.getResponse().getContentAsString());
    }

    @Test
    void scanFilewithCurseWord() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/scanContent")
                .content("Test word : FUCK");
        MvcResult result = mvc.perform(request).andReturn();
        ArrayList arrayWords = new ArrayList();
        arrayWords.add("fuck");
        ScanContentResponse contentResponse = new ScanContentResponse(true , arrayWords );
        String serialized = new ObjectMapper().writeValueAsString(contentResponse);
        String str = result.getResponse().getContentAsString();
        assertEquals(serialized, str);
    }

    @Test
    void scanFilewithoutCurseWord() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/scanContent")
                .content("Test word : APPLE");
        MvcResult result = mvc.perform(request).andReturn();
        ArrayList arrayWords = new ArrayList();
        ScanContentResponse contentResponse = new ScanContentResponse(false , arrayWords );
        String serialized = new ObjectMapper().writeValueAsString(contentResponse);
        String str = result.getResponse().getContentAsString();
        assertEquals(serialized, str);
    }
}