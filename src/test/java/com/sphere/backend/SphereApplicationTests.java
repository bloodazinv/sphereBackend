package com.sphere.backend;

import com.sphere.backend.entity.User;
import com.sphere.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SphereApplicationTests {
    @Autowired
    UserService userService;

    @Test
    void userTest() {
        userService.registerUser(new User("Sarah", "sarah@mail.com","123456"));
    }

}
