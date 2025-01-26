package com.user.controller;


import com.user.dto.UserDto;
import com.user.exception.ServiceResponseException;
import com.user.model.User;
import com.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;



    @Operation(summary = "Get all users", description = "Retrieve a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("API call: Get all users.");
        return ResponseEntity.ok(userService.getAllUsers());
    }



    @Operation(summary = "Load users from external API", description = "Fetch user data from the external API and load it into the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users loaded successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    })
    @PostMapping("/load")
    public ResponseEntity<List<User>> loadUsers() {
        log.info("API call: Load users from external API.");
        return ResponseEntity.ok(userService.loadUsersFromExternalDataAPI());
    }



    @Operation(summary = "Get users by role", description = "Retrieve users filtered by their role.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully by role",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid role specified")
    })
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserDto>> getUsersByRole(@PathVariable String role) {
        log.info("API call: Get users by role: {}", role);
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }


    @Operation(summary = "Get users sorted by age", description = "Retrieve users sorted by age in ascending or descending order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users sorted by age",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)))
    })
    @GetMapping("/sorted")
    public ResponseEntity<List<UserDto>> getUsersSortedByAge(@RequestParam boolean ascending) {
        log.info("API call: Get users sorted by age in {} order.", ascending ? "ascending" : "descending");
        return ResponseEntity.ok(userService.getUsersSortedByAge(ascending));
    }



    @Operation(summary = "Find user by ID or SSN", description = "Retrieve a user by their ID or SSN.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters")
    })
    @GetMapping("/find")
    public ResponseEntity<UserDto> getUserByIdOrSsn(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String ssn) {
        if (id == null && (ssn == null || ssn.isEmpty())) {
            log.warn("API call: Invalid request. Either id or ssn must be provided.");
            throw  ServiceResponseException.badRequest().message("API call: Invalid request. Either id or ssn must be provided.");
        }
        log.info("API call: Find user by id: {} or ssn: {}", id, ssn);
        return ResponseEntity.ok(userService.getUserByIdOrSsn(id, ssn));
    }



    @Operation(summary = "Create a user", description = "Add a new user to the database.")
    @PostMapping("/create")
    public ResponseEntity<User> createUser( @RequestBody UserDto userDto) {
        User createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }


    @Operation(summary = "Update a user", description = "Modify the details of an existing user.")
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,  @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }


    @Operation(summary = "Delete a user", description = "Remove a user from the database by their ID.")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }



}
