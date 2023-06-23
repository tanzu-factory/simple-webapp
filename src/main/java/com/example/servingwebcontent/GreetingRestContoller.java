package com.example.servingwebcontent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.servingwebcontent.models.GreetingResponseObject;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api")
public class GreetingRestContoller {

    private final Logger logger = LoggerFactory.getLogger(GreetingRestContoller.class);

    private final AppProperties appProperties;

    @GetMapping("/healthcheck")
    public GreetingResponseObject healthCheck() {
        logger.info("Inside healthcheck..");
        return new GreetingResponseObject("running");
    }

    @GetMapping({"/greeting/{name}", "/greeting"})
    public GreetingResponseObject greeting(@PathVariable(name = "name", required = false) String name) {
        logger.info("Inside greeting..");
        String pathVarName = StringUtils.isEmpty(name) ? "anonymous" : name; 
        return new GreetingResponseObject(this.appProperties.getGreetingText() + " - responding to: "+ pathVarName);
    }
}
