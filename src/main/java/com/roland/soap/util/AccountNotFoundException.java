package com.roland.soap.util;

/**
 * @author Roland Pilpani 29.12.2022
 */
public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message) {
        super(message);
    }
}
