package com.scaler.demo.project.service;

import com.scaler.demo.project.dto.CartDTO;
import com.scaler.demo.project.dto.CartResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICartService {

    public List<CartDTO> getAllCarts();

}
