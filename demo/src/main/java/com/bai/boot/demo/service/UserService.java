/**
 * Copyright (C), 2018-2018, 东软望海科技有限公司
 * FileName: UserService
 * Author:   bailh
 * Date:     2018/12/20 10:16
 * Description: 用户服务接口
 * Version:  v1.0
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bai.boot.demo.service;

import com.bai.boot.demo.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByUserName(String userName);

    void saveUser(User user);
}
