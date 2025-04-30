package com.scaler.demo.project.cartAndCheckout.repositories;


import com.scaler.demo.project.cartAndCheckout.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long cartId);
}
