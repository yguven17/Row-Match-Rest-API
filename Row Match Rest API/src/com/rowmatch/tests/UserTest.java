package com.rowmatch.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rowmatch.model.User;
import com.rowmatch.service.UserService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        try{
            User user = userService.createUser(new User());
            user.setId(123);
            assertEquals(user.getLevel(), 1);
            assertEquals(user.getCoins(), 5000);
            assertEquals(user.getId(), 123);
        }
        catch (Exception e){
            System.out.println("error");
        }

    }

    @Test
    public void testUpdateLevel() {
        try{
            User user = userService.createUser(new User());
            user = userService.updateLevel(user, 2);
            assertEquals(user.getLevel(), 2);
            assertEquals(user.getCoins(), 5025);
        }
         catch (Exception e){
          System.out.println("error");
        }
    }
}
