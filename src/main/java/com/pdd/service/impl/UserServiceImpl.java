package com.pdd.service.impl;

import com.pdd.dto.request.UserRequestDTO;
import com.pdd.exception.ResourceNotFoundExeption;
import com.pdd.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Override
    public int addUser(UserRequestDTO userDTO) {

        System.out.println("UserServiceImpl.addUser");
        if(!userDTO.getFirstName().equals("Duong")) {
            throw new ResourceNotFoundExeption("Duong khong ton tai");
        }
        return 0;
    }
}
