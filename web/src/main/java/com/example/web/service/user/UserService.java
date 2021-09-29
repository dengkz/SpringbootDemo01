package com.example.web.service.user;


import com.example.web.model.UserDomain;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDomain> getAllUser();
    UserDomain getUserByName(String name);
}
