package com.laurentiuspilca.ssia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
public class MainController {

    private Logger logger =
            Logger.getLogger(MainController.class.getName());

    @GetMapping("/")
    public String main() {
        return "main.html";
    }

    @PostMapping("/test")
    @ResponseBody
//    @CrossOrigin("http://localhost:8080")
    public String test() {
        logger.info("Test method called");
        return "HELLO";
    }
}
