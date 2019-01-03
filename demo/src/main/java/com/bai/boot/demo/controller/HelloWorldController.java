/**
 * Copyright (C), 2018-2018, 东软望海科技有限公司
 * FileName: HelloWorldTest
 * Author:   bailh
 * Date:     2018/12/19 13:15
 * Description: 测试类
 * Version:  v1.0
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bai.boot.demo.controller;

import com.bai.boot.demo.domain.User;
import com.bai.boot.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Slf4j
@RestController
public class HelloWorldController {
    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }

    @RequestMapping("/add")
    String add( ) {
        List<User> userList = new ArrayList<>();
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userList.add(new User("aa1", "aa@126.com", "aa", "aa123456", formattedDate));
        userList.add(new User("bb2", "bb@126.com", "bb", "bb123456", formattedDate));
        userList.add(new User("cc3", "cc@126.com", "cc", "cc123456", formattedDate));
        for (User user : userList) {
            userService.saveUser(user);
        }
        return userList.size() + "";
    }

    @RequestMapping("/list")
    List<User> list() {
        List<User> userList = userService.findAll();
        return  userList;
    }

}

