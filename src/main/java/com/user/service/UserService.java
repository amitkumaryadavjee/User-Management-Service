package com.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.user.dto.UserDto;
import com.user.exception.ServiceResponseException;
import com.user.helper.HelperRestTemplate;
import com.user.mapper.UserMapper;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.util.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private HelperRestTemplate helperRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserValidator userValidator;

    private final ObjectMapper objectMapper =new ObjectMapper();

    // Load data from external API into the database
    @Cacheable("users")
    public List<User> loadUsersFromExternalDataAPI() {
        try {
            Object response = helperRestTemplate.fetchData();
            List<User> users = processResponse(response);
            userRepository.saveAll(users);
            log.info("Successfully loaded {} users into the database.", users.size());
            return users;
        } catch (Exception e) {
            log.error("Failed to load users from external API :{}", e.getMessage());
            throw ServiceResponseException.internalServerError().message("Failed to load users from external API.");
        }
    }

    private List<User> processResponse(Object response) {
        log.info("Processing api response");
        Gson gson = new Gson();
        List<User> userList = new ArrayList<>();
        try {
            String jsonString = gson.toJson(response);
            JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has("users")) {
                JsonArray usersArray = jsonObject.getAsJsonArray("users");
                if (usersArray != null && !usersArray.isEmpty()) {
                    User[] users = new Gson().fromJson(usersArray, User[].class);
                    userList = List.of(users);
                }
            }
        } catch (Exception e) {
            log.error("Some exception occur while processing response of users api data :{}", e.getMessage());
            throw ServiceResponseException.internalServerError().message(e.getMessage());
        }
        return userList;
    }

    // Get all users
    public List<UserDto> getAllUsers() {
        try {
            log.info("Fetching all users.");
            return userRepository.findAll()
                    .stream()
                    .map(userMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Some exception occur while fetching all user from DB :{}", e.getMessage());
            throw ServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    // Get users by role
    public List<UserDto> getUsersByRole(String role) {
        log.info("Fetching users with role: {}", role);
        try {
            return userRepository.findByRole(role)
                    .stream()
                    .map(userMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Some exception occur while fetching users by role from DB :{}", e.getMessage());
            throw ServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    // Get users sorted by age
    public List<UserDto> getUsersSortedByAge(boolean ascending) {
        log.info("Fetching users sorted by age in {} order.", ascending ? "ascending" : "descending");
        try {
            return userRepository.findAll(Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, "age"))
                    .stream()
                    .map(userMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Some exception occur while fetching all user Sorted By Age from DB :{}", e.getMessage());
            throw ServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    // Find user by ID or SSN
    public UserDto getUserByIdOrSsn(Long id, String ssn) {
        log.info("Fetching user by id: {} or ssn: {}", id, ssn);
        try {
            Optional<User> user = userRepository.findByIdOrSsn(id, ssn);
            return user.map(userMapper::toDto).orElseThrow(() -> new RuntimeException("User not found with id: " + id + " or ssn: " + ssn));
        } catch (Exception e) {
            log.error("Some exception occur while fetching user By id or ssn from DB :{}", e.getMessage());
            throw ServiceResponseException.internalServerError().message(e.getMessage());
        }
    }


    // Create a new user
    public User createUser(UserDto userDto) {
        userValidator.validate(userDto);
        try {
            User user = objectMapper.convertValue(userDto, User.class);
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("Some exception occur while creating user :{}", e.getMessage());
            throw ServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    // Update an existing user
    public User updateUser(Long id, UserDto userDto) {
        userValidator.validate(userDto);
        try {
            User existingUser = userRepository.findById(id).orElseThrow(() -> ServiceResponseException.internalServerError().message("User not found "));

            existingUser = objectMapper.updateValue(existingUser, userDto);  // Maps fields from UserDto to existingUser

//            existingUser.setFirstName(userDto.getFirstName());
//            existingUser.setLastName(userDto.getLastName());
//            existingUser.setAge(userDto.getAge());
//            existingUser.setEmail(userDto.getEmail());
//            existingUser.setPhone(userDto.getPhone());
//            existingUser.setRole(userDto.getRole());

            return userRepository.save(existingUser);
        } catch (Exception e) {
            log.error("Some exception occur while creating user :{}", e.getMessage());
            throw ServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw  ServiceResponseException.internalServerError().message("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    // Get a user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> ServiceResponseException.internalServerError().message("User not found with ID: " + id));
    }


//    // Map DTO to Entity
//    private User mapToEntity(UserDto userDto) {
//        User user = new User();
//        user.setFirstName(userDto.getFirstName());
//        user.setLastName(userDto.getLastName());
//        user.setAge(userDto.getAge());
//        user.setEmail(userDto.getEmail());
//        user.setPhone(userDto.getPhone());
//        user.setRole(userDto.getRole());
//        return user;
//    }
//
//    // Map Entity to DTO
//    private UserDto mapToDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setFirstName(user.getFirstName());
//        userDto.setLastName(user.getLastName());
//        userDto.setAge(user.getAge());
//        userDto.setEmail(user.getEmail());
//        userDto.setPhone(user.getPhone());
//        userDto.setRole(user.getRole());
//        return userDto;
//    }


}

