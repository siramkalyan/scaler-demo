package com.scaler.demo.project.controller;

import com.scaler.demo.project.model.Test;
import com.scaler.demo.project.model.TestEntity;
import com.scaler.demo.project.repository.IProductRepository;
import com.scaler.demo.project.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestRepository repository;

    @Autowired
    private IProductRepository iProductRepository;



    @GetMapping("/test")
    public ResponseEntity getTest(){
        return ResponseEntity.ok().body(iProductRepository.getAllProducts());
       // return ResponseEntity.ok().body(repository.findByNameLike("test"));
    }

    @PostMapping("/test")
    public ResponseEntity addTest(@RequestBody Test test){
       // test.setName("test");

        return ResponseEntity.ok().body(repository.save(test));
    }
}
