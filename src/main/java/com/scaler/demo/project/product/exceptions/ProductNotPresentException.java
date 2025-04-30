package com.scaler.demo.project.product.exceptions;

public class ProductNotPresentException extends  Throwable{
    public ProductNotPresentException(String message) {
        super(message);
    }
}
