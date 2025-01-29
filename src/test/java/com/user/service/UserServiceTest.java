package com.user.service;


import com.google.gson.Gson;
import com.user.dto.UserDto;
import com.user.helper.HelperRestTemplate;
import com.user.mapper.UserMapper;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.util.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private HelperRestTemplate helperRestTemplate;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUsersFromExternalDataAPIShouldReturnUsers() {
        // Mock API response
        Gson gson = new Gson();

        // Creating the response as a Map
        Map<String, Object> mockApiResponse = new HashMap<>();
        Map<String, Object> user = new HashMap<>();
        Map<String, Object> address = new HashMap<>();
        Map<String, Object> coordinates = new HashMap<>();
        Map<String, Object> bank = new HashMap<>();
        Map<String, Object> company = new HashMap<>();
        Map<String, Object> companyAddress = new HashMap<>();
        Map<String, Object> crypto = new HashMap<>();
        Map<String, Object> hair = new HashMap<>();

        coordinates.put("lat", -77.16213);
        coordinates.put("lng", -92.084824);

        address.put("address", "626 Main Street");
        address.put("city", "Phoenix");
        address.put("state", "Mississippi");
        address.put("stateCode", "MS");
        address.put("postalCode", "29112");
        address.put("coordinates", coordinates);
        address.put("country", "United States");

        bank.put("cardExpire", "03/26");
        bank.put("cardNumber", "9289760655481815");
        bank.put("cardType", "Elo");
        bank.put("currency", "CNY");
        bank.put("iban", "YPUXISOBI7TTHPK2BR3HAIXL");

        companyAddress.put("address", "263 Tenth Street");
        companyAddress.put("city", "San Francisco");
        companyAddress.put("state", "Wisconsin");
        companyAddress.put("stateCode", "WI");
        companyAddress.put("postalCode", "37657");
        companyAddress.put("coordinates", coordinates);
        companyAddress.put("country", "United States");

        company.put("department", "Engineering");
        company.put("name", "Dooley, Kozey and Cronin");
        company.put("title", "Sales Manager");
        company.put("address", companyAddress);

        crypto.put("coin", "Bitcoin");
        crypto.put("wallet", "0xb9fc2fe63b2a6c003f1c324c3bfa53259162181a");
        crypto.put("network", "Ethereum (ERC20)");

        hair.put("color", "Brown");
        hair.put("type", "Curly");

        user.put("id", 1);
        user.put("firstName", "Emily");
        user.put("lastName", "Johnson");
        user.put("maidenName", "Smith");
        user.put("age", 28);
        user.put("gender", "female");
        user.put("email", "emily.johnson@x.dummyjson.com");
        user.put("phone", "+81 965-431-3024");
        user.put("username", "emilys");
        user.put("password", "emilyspass");
        user.put("birthDate", "1996-5-30");
        user.put("image", "https://dummyjson.com/icon/emilys/128");
        user.put("bloodGroup", "O-");
        user.put("height", 193.24);
        user.put("weight", 63.16);
        user.put("eyeColor", "Green");
        user.put("hair", hair);
        user.put("ip", "42.48.100.32");
        user.put("address", address);
        user.put("macAddress", "47:fa:41:18:ec:eb");
        user.put("university", "University of Wisconsin--Madison");
        user.put("bank", bank);
        user.put("company", company);
        user.put("ein", "977-175");
        user.put("ssn", "900-590-289");
        user.put("userAgent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36");
        user.put("crypto", crypto);
        user.put("role", "admin");

        mockApiResponse.put("users", new Object[]{user});

        when(helperRestTemplate.fetchData()).thenReturn(mockApiResponse);
        List<User> mockUsers= userService.processResponse(mockApiResponse);


        when(userRepository.saveAll(anyList())).thenReturn(mockUsers);

        // Act
        List<User> result = userService.loadUsersFromExternalDataAPI();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Emily", result.get(0).getFirstName());
        verify(userRepository, times(1)).saveAll(anyList());
    }


    @Test
    void getAllUsersShouldReturnAllUsers() {
        //mock user
        User user = new User();
        user.setId(1L);
        user.setFirstName("Emily");
        user.setLastName("Johnson");
        user.setAge(30);
        user.setRole("admin");

        //mock userDto
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("Emily");
        userDto.setLastName("Johnson");
        userDto.setAge(30);
        userDto.setRole("admin");

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toDto(user)).thenReturn(userDto);

        List<UserDto> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Emily", result.get(0).getFirstName());
        verify(userRepository, times(1)).findAll();
    }


    @Test
    void getUsersByRole_shouldReturnFilteredUsers() {
        // Arrange
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setFirstName("John");
        mockUser.setLastName("Deo");
        mockUser.setAge(30);
        mockUser.setRole("admin");

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("John");
        userDto.setLastName("Deo");
        userDto.setAge(30);
        userDto.setRole("admin");
        when(userRepository.findByRole("admin")).thenReturn(List.of(mockUser));
        when(userMapper.toDto(mockUser)).thenReturn(userDto);

        // Act
        List<UserDto> result = userService.getUsersByRole("admin");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("admin", result.get(0).getRole());
        verify(userRepository, times(1)).findByRole("admin");
    }


    @Test
    void deleteUser_shouldDeleteUserById() {
        // Arrange
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);

        // Act
        userService.deleteUser(1L);

        // Assert
        verify(userRepository, times(1)).deleteById(1L);
    }


    @Test
    void createUser_shouldValidateAndSaveUser() {
        // Arrange
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("John");
        userDto.setLastName("Deo");
        userDto.setAge(30);
        userDto.setRole("admin");
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");

        doNothing().when(userValidator).validate(userDto);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User result = userService.createUser(userDto);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(userValidator, times(1)).validate(userDto);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser_shouldUpdateAndReturnUser() {
        // Arrange
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setFirstName("John");
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("John");
        userDto.setLastName("Deo");
        userDto.setAge(30);
        userDto.setRole("admin");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        // Act
        User result = userService.updateUser(1L, userDto);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void deleteUser_shouldDeleteUserWhenExists() {
        // Arrange
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);

        // Act
        userService.deleteUser(1L);

        // Assert
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void getUserById_shouldReturnUserWhenExists() {
        // Arrange
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(userRepository, times(1)).findById(1L);
    }





}
