package com.example.web.dao;

import com.example.web.model.UserDomain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<UserDomain> getAllUser();
    UserDomain getUserByName(String name);
}
