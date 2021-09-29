package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class JDBCController {

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping ("/jdbc")
    @ResponseBody
    public String jdbc() throws SQLException {

        String result = "";

        //查看一下默认的数据源
        System.out.println(dataSource.getClass());

        //获取数据连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

        System.out.println("getDBInfo():"+getDBInfo());

        return result;
    }

    public String getDBInfo(){
        String sql = "select * from user";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);

        String name = list.toString();

        return name;

    }


}
