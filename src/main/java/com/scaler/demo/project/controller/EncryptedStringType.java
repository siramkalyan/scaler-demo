package com.scaler.demo.project.controller;

public class EncryptedStringType extends EncryptedType<String>{


    /**
     * Constructor to initialize the type class.
     *
     * @param typeClass The class type of the entity attribute.
     */
    public EncryptedStringType(Class<String> typeClass) {
        super(typeClass);
    }

    public EncryptedStringType(){
        super(String.class);
    }

}
