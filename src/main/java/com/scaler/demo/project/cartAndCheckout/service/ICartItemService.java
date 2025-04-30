package com.scaler.demo.project.cartAndCheckout.service;


import com.scaler.demo.project.cartAndCheckout.models.CartItem;
import com.scaler.demo.project.product.exceptions.ProductNotPresentException;

public interface ICartItemService {
    void addCartItem(Long cartId, Long productId, int quantity) throws ProductNotPresentException;
    void removeCartItem(Long cartId, Long productId) throws ProductNotPresentException;
    void updateItemQuantity(Long cartId, Long productId, int quantity) throws ProductNotPresentException;

    CartItem getCartItem(Long cartId, Long productId);
}
