package com.user.dto;

import lombok.Data;

@Data
public class BankDto {
    private String cardExpire;
    private String cardNumber;
    private String cardType;
    private String currency;
    private String iban;
}
