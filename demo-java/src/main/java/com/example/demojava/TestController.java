package com.example.demojava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.FormSubmitEvent;
import java.awt.*;
import java.lang.invoke.MethodType;

@RestController
@Slf4j
public class TestController {
    @GetMapping
    public String getData(){
        return "/index.html";
    }
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getResults(@RequestBody String data){
        log.info(data);
        return "got it";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getResultsTwo(@RequestBody String data){
        log.info(data);
        return "got it";
    }

    @PostMapping("magic")
    public String getWithReqParm(@RequestParam String name, @RequestParam String data){
        log.info(name);
        return name + data;
    }
}
