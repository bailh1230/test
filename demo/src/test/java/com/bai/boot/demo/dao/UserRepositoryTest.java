package com.bai.boot.demo.dao;

import com.bai.boot.demo.dao.read.UserReadRepository;
import com.bai.boot.demo.dao.write.UserWriteRepository;
import com.bai.boot.demo.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserWriteRepository userWriteRepository;

    @Autowired
    private UserReadRepository userReadRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test

    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userWriteRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456", formattedDate));
        userWriteRepository.save(new User("bb2", "bb@126.com", "bb", "bb123456", formattedDate));
        userWriteRepository.save(new User("cc3", "cc@126.com", "cc", "cc123456", formattedDate));

        Assert.assertEquals(3, userReadRepository.findAll().size());
        Assert.assertEquals(2, userReadRepository.findByUserNameOrEmail("bb2", "cc@126.com").size());
        userWriteRepository.delete(userReadRepository.findByUserName("aa1"));
    }
}