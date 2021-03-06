/**
 * Copyright (C), 2018-2018, 东软望海科技有限公司
 * FileName: UserServiceImp
 * Author:   bailh
 * Date:     2018/12/20 10:22
 * Description: 用户服务实现类
 * Version:  v1.0
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bai.boot.demo.service.imp;

import com.bai.boot.demo.dao.read.UserReadRepository;
import com.bai.boot.demo.dao.write.UserWriteRepository;
import com.bai.boot.demo.domain.User;
import com.bai.boot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImp implements UserService {
    private UserWriteRepository userWriteRepository;
    private UserReadRepository userReadRepository;
    @Autowired
    public UserServiceImp(UserWriteRepository userWriteRepository, UserReadRepository userReadRepository) {
        this.userWriteRepository = userWriteRepository;
        this.userReadRepository = userReadRepository;
    }

    @Override
    public List<User> findAll() {
        return userReadRepository.findAll();
    }

    @Override
    public User findByUserName(String userName) {
        return userReadRepository.findByUserName(userName);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveUser(User user) {
        userWriteRepository.save(user);
    }
}
