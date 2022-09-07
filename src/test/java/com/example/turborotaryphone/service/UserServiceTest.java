package com.example.turborotaryphone.service;

import com.example.turborotaryphone.model.Role;
import com.example.turborotaryphone.model.User;
import com.example.turborotaryphone.repos.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void addUserTest() {
        User user = new User();

        boolean isUserCreated = userService.addUser(user);

        Assertions.assertTrue(isUserCreated);
        Assertions.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(user.getPassword());
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    void addUserFailTest() {
        User user = new User();

        user.setUsername("Tom");

        Mockito.doReturn(new User())
                .when(userRepo)
                .findByUsername("Tom");

        boolean isUserCreated = userService.addUser(user);

        Assertions.assertFalse(isUserCreated);

        Mockito.verify(passwordEncoder, Mockito.times(0)).encode(user.getPassword());
        Mockito.verify(userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }

}
