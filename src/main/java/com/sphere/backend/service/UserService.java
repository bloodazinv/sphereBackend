/**
 * FileName: UserService
 * Author: jane
 * Date: 2023/4/9 19:00
 * Description:
 * Version:
 */

package com.sphere.backend.service;

import com.sphere.backend.entity.User;
import com.sphere.backend.repository.UserRepo;
import com.sphere.backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserService implements UserServiceImpl {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Override
    public boolean isEmailUnique(String email) {
        User user = userRepo.findByEmail(email);
        return user == null;
    }

    @Override
    public boolean isUsernameUnique(String username) {
        User user = userRepo.findByUsername(username);
        return user == null;
    }

    @Override
    public User registerUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.setRegisterTime(new Date());
        return userRepo.save(user);
    }
}
