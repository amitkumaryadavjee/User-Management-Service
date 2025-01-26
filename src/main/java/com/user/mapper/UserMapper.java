package com.user.mapper;
import com.user.dto.*;
import com.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setMaidenName(user.getMaidenName());
        dto.setAge(user.getAge());
        dto.setGender(user.getGender());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setBirthDate(user.getBirthDate());
        dto.setImage(user.getImage());
        dto.setBloodGroup(user.getBloodGroup());
        dto.setHeight(user.getHeight());
        dto.setWeight(user.getWeight());
        dto.setEyeColor(user.getEyeColor());
        dto.setIp(user.getIp());
        dto.setMacAddress(user.getMacAddress());
        dto.setUniversity(user.getUniversity());
        dto.setEin(user.getEin());
        dto.setSsn(user.getSsn());
        dto.setUserAgent(user.getUserAgent());
        dto.setRole(user.getRole());

        // Map nested objects
        if (user.getHair() != null) {
            HairDto hairDto = new HairDto();
            hairDto.setColor(user.getHair().getColor());
            hairDto.setType(user.getHair().getType());
            dto.setHair(hairDto);
        }

        if (user.getAddress() != null) {
            AddressDto addressDto = new AddressDto();
            addressDto.setAddress(user.getAddress().getAddress());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setState(user.getAddress().getState());
            addressDto.setStateCode(user.getAddress().getStateCode());
            addressDto.setPostalCode(user.getAddress().getPostalCode());
            addressDto.setCountry(user.getAddress().getCountry());

            if (user.getAddress().getCoordinates() != null) {
                CoordinatesDto coordinatesDto = new CoordinatesDto();
                coordinatesDto.setLat(user.getAddress().getCoordinates().getLat());
                coordinatesDto.setLng(user.getAddress().getCoordinates().getLng());
                addressDto.setCoordinates(coordinatesDto);
            }

            dto.setAddress(addressDto);
        }

        if (user.getBank() != null) {
            BankDto bankDto = new BankDto();
            bankDto.setCardExpire(user.getBank().getCardExpire());
            bankDto.setCardNumber(user.getBank().getCardNumber());
            bankDto.setCardType(user.getBank().getCardType());
            bankDto.setCurrency(user.getBank().getCurrency());
            bankDto.setIban(user.getBank().getIban());
            dto.setBank(bankDto);
        }

        if (user.getCompany() != null) {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setName(user.getCompany().getName());
            companyDto.setDepartment(user.getCompany().getDepartment());
            companyDto.setTitle(user.getCompany().getTitle());

            if (user.getCompany().getAddress() != null) {
                AddressDto companyAddressDto = new AddressDto();
                companyAddressDto.setAddress(user.getCompany().getAddress().getAddress());
                companyAddressDto.setCity(user.getCompany().getAddress().getCity());
                companyAddressDto.setState(user.getCompany().getAddress().getState());
                companyAddressDto.setStateCode(user.getCompany().getAddress().getStateCode());
                companyAddressDto.setPostalCode(user.getCompany().getAddress().getPostalCode());
                companyAddressDto.setCountry(user.getCompany().getAddress().getCountry());

                if (user.getCompany().getAddress().getCoordinates() != null) {
                    CoordinatesDto companyCoordinatesDto = new CoordinatesDto();
                    companyCoordinatesDto.setLat(user.getCompany().getAddress().getCoordinates().getLat());
                    companyCoordinatesDto.setLng(user.getCompany().getAddress().getCoordinates().getLng());
                    companyAddressDto.setCoordinates(companyCoordinatesDto);
                }

                companyDto.setAddress(companyAddressDto);
            }

            dto.setCompany(companyDto);
        }

        if (user.getCrypto() != null) {
            CryptoDto cryptoDto = new CryptoDto();
            cryptoDto.setCoin(user.getCrypto().getCoin());
            cryptoDto.setWallet(user.getCrypto().getWallet());
            cryptoDto.setNetwork(user.getCrypto().getNetwork());
            dto.setCrypto(cryptoDto);
        }

        return dto;
    }
}
