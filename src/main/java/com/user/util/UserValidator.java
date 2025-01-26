package com.user.util;

import com.user.dto.UserDto;
import com.user.exception.ServiceResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserValidator {

    public void validate(UserDto userDto) {
        if (userDto == null) {
            throw ServiceResponseException.badRequest().message("User data are required");
        }

        // Validate each field for null or empty
        validateField(userDto.getFirstName(), "First name");
        validateField(userDto.getEmail(), "Email");
        validateField(userDto.getPhone(), "Phone");
        if(userDto.getAge()==0){
            throw ServiceResponseException.badRequest().message("Age is required");
        }
        validateField(userDto.getGender(), "Gender");
        validateField(userDto.getUsername(), "Username");
        validateField(userDto.getPassword(), "Password");
        validateField(userDto.getRole(), "Role");

        if (userDto.getAddress() == null) {
            throw ServiceResponseException.badRequest().message("Address data are required");
        }

        validateField(userDto.getAddress().getAddress(), "Address");
        validateField(userDto.getAddress().getCity(), "City");
        validateField(userDto.getAddress().getState(), "state");
        validateField(userDto.getAddress().getCountry(), "Country");
        if (userDto.getBank() == null) {
            throw ServiceResponseException.badRequest().message("Bank data are required");
        }
        validateField(userDto.getBank().getCardNumber(), "Card Number");
        validateField(userDto.getBank().getCardType(), "Card Type");
        validateField(userDto.getBank().getCardExpire(), "Card Expire");


        if (userDto.getCompany() == null) {
            throw ServiceResponseException.badRequest().message("Company data are required");
        }
        validateField(userDto.getCompany().getName(), "Company Name");
        validateField(userDto.getCompany().getDepartment(), "Company Department");
        validateField(userDto.getCompany().getTitle(), "Company Title");

    }

    private void validateField(String fieldValue, String fieldName) {
        if (fieldValue == null || fieldValue.trim().isEmpty()) {
            throw  ServiceResponseException.badRequest().message(fieldName + " cannot be null or empty");
        }
    }


}
