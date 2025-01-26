package com.user.dto;

import lombok.Data;

@Data
public class CompanyDto {
    private String name;
    private String department;
    private String title;
    private AddressDto address;
}
