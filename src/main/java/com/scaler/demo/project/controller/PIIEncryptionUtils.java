package com.scaler.demo.project.controller;

import org.springframework.stereotype.Component;

@Component
public  class PIIEncryptionUtils {
    public static  String encrypt(String value){
        return "v1" + value + "v1";
    }
    public static String decrypt(String value){
        return value.replaceAll("v1","");
    }
}
