package com.scaler.demo.project.cartAndCheckout.controllers;

import com.scaler.demo.project.cartAndCheckout.models.Cart;
import com.scaler.demo.project.cartAndCheckout.service.ICartItemService;
import com.scaler.demo.project.cartAndCheckout.service.ICartService;
import com.scaler.demo.project.common.exceptions.ResourceNotFoundException;
import com.scaler.demo.project.dto.model.mappedSuperClass.User;
import com.scaler.demo.project.model.ApiResponse;
import com.scaler.demo.project.product.exceptions.ProductNotPresentException;
import com.scaler.demo.project.product.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api_prefix}/cartItems")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final ICartService cartService;
    private final IProductService productService;
   // private final IUserService userService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(
                                                     @RequestParam Long productId,
                                                     @RequestParam Integer quantity) {
        try {
            User user = new User();
            Cart cart = cartService.initializeNewCart(user);
            cartItemService.addCartItem(cart.getId(), productId, quantity);
            return ResponseEntity.ok().body(new ApiResponse("Item added to cart", null));
        } catch(Exception e) {
            return ResponseEntity.status(UNAUTHORIZED).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItem(@PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            cartItemService.removeCartItem(cartId, itemId);
            return ResponseEntity.ok().body(new ApiResponse("Item removed from cart", null));
        } catch (ResourceNotFoundException | ProductNotPresentException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
                                                  @PathVariable Long itemId,
                                                  @RequestParam Integer quantity) {
        try {
            cartItemService.updateItemQuantity(cartId, itemId, quantity);
            return ResponseEntity.ok().body(new ApiResponse("Item updated", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
