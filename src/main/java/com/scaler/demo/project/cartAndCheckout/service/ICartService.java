package com.scaler.demo.project.cartAndCheckout.service;


import com.scaler.demo.project.cartAndCheckout.models.Cart;
import com.scaler.demo.project.dto.model.mappedSuperClass.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long cartId);
    void clearCart(Long cartId);
    BigDecimal getTotalPrice(Long cartId);
    Cart initializeNewCart(User user);
    Cart getCartByUserId(Long userId);
}
