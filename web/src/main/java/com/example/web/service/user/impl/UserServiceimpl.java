package com.example.web.service.user.impl;

import com.example.web.dao.UserMapper;
import com.example.web.model.UserDomain;
import com.example.web.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDomain> getAllUser() {
        List<UserDomain> list = userMapper.getAllUser();

        return list;
    }


    @Override
    public UserDomain getUserByName(String name) {

        UserDomain userDomain = userMapper.getUserByName(name);
        return userDomain;
    }
}
