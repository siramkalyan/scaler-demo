package com.scaler.demo.project.cartAndCheckout.dtos;

import com.scaler.demo.project.cartAndCheckout.models.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StripeResponseDto {
    private String sessionId;
    private String sessionUrl;
    private PaymentStatus status;
    private String message;

    public StripeResponseDto(String sessionId) {
        this.sessionId = sessionId;
    }
}
