package com.pdd.service;

import com.pdd.dto.request.UserRequestDTO;

public interface UserService {
    int addUser(UserRequestDTO userDTO);
}
