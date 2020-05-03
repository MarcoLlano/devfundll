package com.jalasoft.practice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @PostMapping
    public String sayHello(@RequestParam(value="name") String name,
                           @RequestParam(value="lastName") String lastName,
                           @RequestParam(value="file") MultipartFile file) {
        File convertedFile = new File(System.getProperty("user.dir") + "/inputFiles/" + Objects.requireNonNull(file.getOriginalFilename()));
        try {
            file.transferTo(convertedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Hello " + name + " " + lastName;
    }
}
