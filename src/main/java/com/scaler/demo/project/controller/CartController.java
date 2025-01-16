package com.scaler.demo.project.controller;

import com.scaler.demo.project.dto.CartDTO;
import com.scaler.demo.project.dto.CartResponse;
import com.scaler.demo.project.service.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    Logger logger = LoggerFactory.getLogger(CartController.class);
    @GetMapping
    private List<CartDTO> loadAllCarts(){
        logger.info("Started calling cart Service");
        return cartService.getAllCarts();
    }
}
