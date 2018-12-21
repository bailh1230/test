/**
 * Copyright (C), 2018-2018, 东软望海科技有限公司
 * FileName: UserRepository
 * Author:   bailh
 * Date:     2018/12/19 15:01
 * Description: 用户服务类
 * Version:  v1.0
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bai.boot.demo.dao;

import com.bai.boot.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    List<User> findByUserNameOrEmail(String username, String email);
}
