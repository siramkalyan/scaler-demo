package com.scaler.demo.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scaler.demo.project.dto.CartDTO;
import com.scaler.demo.project.dto.CartResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICartService {

    public List<CartDTO> getAllCarts() throws JsonProcessingException;

    public CartDTO addCart(CartDTO cartDTO);

    public CartDTO updateCart(Long id, CartDTO cartDTO);

    public void deleteCart(Long cartId);

}
