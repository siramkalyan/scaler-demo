package com.scaler.demo.project.cartAndCheckout.controllers;


import com.scaler.demo.project.cartAndCheckout.dtos.CheckoutItemRequestDto;
import com.scaler.demo.project.cartAndCheckout.dtos.StripeResponseDto;
import com.scaler.demo.project.cartAndCheckout.service.CheckoutService;
import com.scaler.demo.project.common.dtos.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api_prefix}/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    /**
     * Endpoint to create a payment session for the provided checkout items.
     *
     * @param checkoutItemDtoList List of items for the checkout session
     * @return ResponseEntity containing ApiResponse with Stripe session details or an error message
     */
    @PostMapping("/create-session")
    public ResponseEntity<ApiResponse> createCheckoutSession(@RequestBody List<CheckoutItemRequestDto> checkoutItemDtoList) {
        try {
            // Create a Stripe checkout session
            StripeResponseDto stripeResponse = checkoutService.createSession(checkoutItemDtoList);
            return ResponseEntity.ok(new ApiResponse("Checkout session created successfully.!", stripeResponse));
        } catch (Exception stripeException) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Stripe error: " + stripeException.getMessage(), null));
        } catch (Exception exception) {
            // Handle unexpected errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            new ApiResponse("An unexpected error occurred: " +  exception.getMessage(), null)
                    );
        }
    }
}
