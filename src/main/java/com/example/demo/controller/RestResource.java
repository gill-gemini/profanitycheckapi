package com.example.demo.controller;

import com.example.demo.model.ScanContentResponse;
import com.example.demo.process.inspectFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RestResource {

    @RequestMapping("/")
    public static ResponseEntity<String> getTest() {
        return new ResponseEntity<>(
                "Hi Visitor, check the API documentation for profanity check !",
                HttpStatus.OK);
    }

    @PostMapping("/censorcontent")
    public ResponseEntity<String> censorFile(@RequestBody String body) {

        inspectFile inspect =  new inspectFile();
        String output;
        output = inspect.getCensoredText(body);
        return new ResponseEntity<>(
                 output,
                HttpStatus.OK);
    }

    @PostMapping("/scancontent")
    public ResponseEntity<String> scanFile(@RequestBody String body) throws JsonProcessingException {

        inspectFile inspect =  new inspectFile();
        Boolean output;
        ArrayList<String> arrayWords = inspect.scanCurseWords(body);
        ScanContentResponse resp = new ScanContentResponse(!arrayWords.isEmpty(),arrayWords);
        String serialized = new ObjectMapper().writeValueAsString(resp);
        return new ResponseEntity<>(
                serialized,
                HttpStatus.OK);
    }

}
