package com.example.demo.service;

import com.example.demo.process.inspectFile;
import com.example.demo.model.AuthenticationResponse;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class SalesforceAPIService {

    public static AuthenticationResponse login(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();

        params.add("username","gill.light@dev.org");
        params.add("password", "");
        params.add("client_secret","");
        params.add("client_id", "");
        params.add("grant_type","password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.postForEntity("https://login.salesforce.com/services/oauth2/token", request, AuthenticationResponse.class);
        return (AuthenticationResponse) response.getBody();
    }

    public static void getContentVersionData(String accessToken, String instanceUrl) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + accessToken);
            MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<byte[]> response = restTemplate.exchange(instanceUrl + "/services/data/v52.0/sobjects/ContentVersion/0681t00000yL7fXAAS/VersionData", HttpMethod.GET, request, byte[].class);
            String content  = new String(response.getBody(), StandardCharsets.UTF_8);
            String out = inspectFile.getCensoredText(content);
            System.out.println("out" + out);
            Files.write(Paths.get("/Users/gill.bilal/Desktop/BadW/testCensored.txt"), Collections.singleton(out));
            Files.write(Paths.get("/Users/gill.bilal/Desktop/BadW/testCensored.txt"), response.getBody());
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
