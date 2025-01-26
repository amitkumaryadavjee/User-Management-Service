package com.user.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private Integer age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private Double height;
    private Double weight;
    private String eyeColor;

    private HairDto hair;
    private AddressDto address;
    private String ip;
    private String macAddress;
    private String university;

    private BankDto bank;
    private CompanyDto company;

    private String ein;
    private String ssn;
    private String userAgent;

    private CryptoDto crypto;
    private String role;
}


