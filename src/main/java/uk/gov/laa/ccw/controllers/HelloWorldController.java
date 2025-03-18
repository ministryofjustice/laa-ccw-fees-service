package uk.gov.laa.ccw.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloWorldController {

    @RequestMapping("/")
    String home() {
        log.info("logging test");
        return "Hello World!";
    }


}
