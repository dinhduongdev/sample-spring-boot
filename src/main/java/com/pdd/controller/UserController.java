package com.pdd.controller;


import com.pdd.configuration.Translator;
import com.pdd.dto.request.UserRequestDTO;
import com.pdd.dto.response.ResponseData;
import com.pdd.dto.response.ResponseError;
import com.pdd.exception.ResourceNotFoundExeption;
import com.pdd.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
@Tag(name = "User Controller")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "Add user", description = "API create new user ")
    @PostMapping("/")
    public ResponseData<Integer> addUser(@RequestBody @Valid UserRequestDTO userDTO) {
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("user.add.success"), 1);
    }

    @Operation(summary = "Update user", description = "API update user ")
    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@PathVariable @Min(1) Long userId, @Valid @RequestBody UserRequestDTO userDTO) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated successfully");
    }

    @Operation(summary = "Change status user", description = "API change status user ")
    @PatchMapping("/{userId}")
    public ResponseData<?> changeStatus(@PathVariable Long userId, @RequestParam(required = false) Boolean status) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User status changed successfully");
    }

    @Operation(summary = "Delete user", description = "API delete user ")
    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUser(@PathVariable Long userId) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted successfully");
    }

    @Operation(summary = "Get user detail", description = "API get user detail")
    @GetMapping("/{userId}")
    public ResponseData<UserRequestDTO> getUser(@PathVariable Long userId) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "User retrieved successfully",
                new UserRequestDTO("firstName", "lastName", "phone", "email"));
    }


    @Operation(summary = "Get list user", description = "API get list user ")
    @GetMapping("/list")
    public ResponseData<List<UserRequestDTO>> getAllUsers(
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ResponseData<>(
                HttpStatus.OK.value(),
                "list",
                List.of(new UserRequestDTO("firstName", "lastName", "phone", "email")));
    }
}
