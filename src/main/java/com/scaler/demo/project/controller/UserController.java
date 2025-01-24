package com.scaler.demo.project.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{from}/{to}")
    public String getName(@PathVariable("from") String userName, @PathVariable(name = "to", required = false) String to){
        return "Hello from " + userName + "to " + to;
    }

    @GetMapping()
    public String getNameByRequestParam(
            @RequestParam(name = "from") String userName,
            @RequestParam(name = "to", required = false) String toName,
            @RequestParam(name = "createdAt") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate createdDate
            ){
        return "Hello from " + userName + "to" + toName;
    }
}
