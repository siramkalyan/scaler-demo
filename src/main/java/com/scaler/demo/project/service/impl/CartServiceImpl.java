package com.scaler.demo.project.service.impl;

import com.scaler.demo.project.dto.CartDTO;
import com.scaler.demo.project.dto.CartResponse;
import com.scaler.demo.project.service.ICartService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    private static final String GET_CART_API_ENDPOINT = "https://fakestoreapi.com/carts";
    @Override
    public List<CartDTO> getAllCarts() {
        RestTemplate restTemplate = new RestTemplate();

       ResponseEntity<List> cartDTO =  restTemplate.getForEntity(GET_CART_API_ENDPOINT, List.class);
       List<CartDTO> carts = cartDTO.getBody();
        return carts;
    }
}
