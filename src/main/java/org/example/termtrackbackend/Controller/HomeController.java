package org.example.termtrackbackend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "TermTrack Backend API is running. Frontend: https://eshote.github.io/IPDdemo/";
    }
}