package com.scaler.demo.project.cartAndCheckout.repositories;

import com.scaler.demo.project.cartAndCheckout.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserId(Long userId);
}
