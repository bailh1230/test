package com.bai.boot.demo.service.imp;

import com.bai.boot.demo.dao.UserRepository;
import com.bai.boot.demo.domain.User;
import com.bai.boot.demo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImpTest {
    @Autowired
    private UserService userService;
    private List<User> userList;

    @Before
    public void setUp() throws Exception {
        userList = new ArrayList<>();
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userList.add(new User("aa1", "aa@126.com", "aa", "aa123456", formattedDate));
        userList.add(new User("bb2", "bb@126.com", "bb", "bb123456", formattedDate));
        userList.add(new User("cc3", "cc@126.com", "cc", "cc123456", formattedDate));
    }

    @Test
    public void saveUser() {
        for (User user : userList) {
            userService.saveUser(user);
        }
        Assert.assertEquals(3, userService.findAll().size());
    }

    @Test
    public void findByUserName() {
        User user = userService.findByUserName("bb2");
        Assert.assertEquals("bb", user.getNickName());
    }


}