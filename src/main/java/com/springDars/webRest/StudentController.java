package com.springDars.webRest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController {
    @GetMapping("/student")
    public String hello(){
        return "Salom";
    }
    @GetMapping("/talaba")
    public String talaba(){
        return "talabalar";
    }
    @GetMapping("/talabalar")
    public String talabalar(){
        return "Talabalarga salom";
    }
}
