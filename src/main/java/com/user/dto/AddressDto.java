package com.user.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String address;
    private String city;
    private String state;
    private String stateCode;
    private String postalCode;
    private CoordinatesDto coordinates;
    private String country;
}
