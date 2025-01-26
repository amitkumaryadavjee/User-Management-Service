package com.user.controller;
//
//import com.user.dto.UserDto;
//import com.user.service.UserService;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import java.util.List;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@AutoConfigureMockMvc
public class UserControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void testGetAllUsers() throws Exception {
//        // Mock data
//        UserDto userDto1=new UserDto();
//        userDto1.setFirstName("Amit");
//        userDto1.setAge(24);
//        userDto1.setRole("admin");
//        List<UserDto> mockUsers = List.of(userDto1);
//        when(userService.getAllUsers()).thenReturn(mockUsers);
//
//        // Perform GET request and validate response
//        mockMvc.perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()").value(2))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].firstName").value("Amit")
//                );
//    }
//
//    @Test
//    public void testGetUsersByRole() throws Exception {
//        // Mock data
//        UserDto userDto1=new UserDto();
//        userDto1.setFirstName("Amit");
//        userDto1.setAge(24);
//        userDto1.setRole("admin");
//        List<UserDto> mockUsers = List.of(userDto1);
//        // Mock service behavior
//        when(userService.getUsersByRole("admin")).thenReturn(mockUsers);
//
//        // Perform GET request and validate response
//        mockMvc.perform(get("/api/users/role/admin"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.length()").value(1))
//                .andExpect(jsonPath("$[0].role").value("admin"))
//                .andExpect(jsonPath("$[0].firstName").value("Emily"));
//    }
//
//    @Test
//    public void testGetUserByIdOrSsn() throws Exception {
//        // Mock data
//        UserDto mockUser=new UserDto();
//        mockUser.setFirstName("Amit");
//        mockUser.setAge(24);
//        mockUser.setRole("admin");
//
//        when(userService.getUserByIdOrSsn(1L, null)).thenReturn(mockUser);
//
//        // Perform GET request and validate response
//        mockMvc.perform(get("/api/users/find?id=1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.firstName").value("Amit"))
//                .andExpect(jsonPath("$.role").value("admin"));
//    }
//
//    @Test
//    public void testGetUserByIdOrSsnInvalidParameters() throws Exception {
//        // Perform GET request without id or ssn
//        mockMvc.perform(get("/api/users/find"))
//                .andExpect(status().isBadRequest());
//    }
}

