package com.scaler.demo.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scaler.demo.project.dto.CartDTO;
import com.scaler.demo.project.dto.CartResponse;
import com.scaler.demo.project.service.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    Logger logger = LoggerFactory.getLogger(CartController.class);
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    private ResponseEntity<List<CartDTO>> loadAllCarts() throws JsonProcessingException {
        logger.info("Started calling cart Service");
//        List<CartDTO> response =  cartService.getAllCarts();
//        return new ResponseEntity<>(response, HttpStatus.OK);
        ResponseEntity<List<CartDTO>> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<CartDTO> response  = cartService.getAllCarts();
        if(!CollectionUtils.isEmpty(response)){
            responseEntity = new ResponseEntity<>(response,HttpStatus.OK);
        }
        return responseEntity;
    }

    @PostMapping
    private ResponseEntity<CartDTO> addCart(@RequestBody CartDTO cartDTO ){
        //ResponseEntity<List<CartDTO>> cartresponse  = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        CartDTO cartDTO1 = cartService.addCart(cartDTO);
        return new ResponseEntity<>(cartDTO1,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody CartDTO cart){
        ResponseEntity<CartDTO> cartResponse = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        CartDTO cartDTO = cartService.updateCart(id, cart);
        return new ResponseEntity<>(cartDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteCart(@PathVariable Long id){
        return cartService.deleteCart(id);
    }
}
