/**
 * FileName: UserService
 * Author: jane
 * Date: 2023/4/9 19:00
 * Description:
 * Version:
 */

package com.sphere.backend.service;

import com.sphere.backend.convert.GroupConvert;
import com.sphere.backend.convert.UserConvert;
import com.sphere.backend.dto.GroupDto;
import com.sphere.backend.dto.UserDto;
import com.sphere.backend.entity.User;
import com.sphere.backend.entity.UserGroup;
import com.sphere.backend.repository.UserGroupRepo;
import com.sphere.backend.repository.UserRepo;
import com.sphere.backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserServiceImpl {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserGroupRepo userGroupRepo;


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
    public UserDto registerUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.setRegisterTime(new Date());
        return UserConvert.INSTANCE.po2dto(userRepo.save(user));
    }

    @Override
    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    @Override
    public List<GroupDto> findGroupsOfUser(String username) {
        User user = findByUsername(username);
        List<UserGroup> usergroups = user.getUserGroups();

        List<GroupDto> groupsOfUser = new ArrayList<>();
        for (UserGroup usergroup : usergroups) {
            groupsOfUser.add(GroupConvert.INSTANCE.po2dto(usergroup.getGroup()));
        }
        return groupsOfUser;

    }

    @Override
    public User findById(Long Id){
        Optional<User> userOptional = userRepo.findById(Id);
        return userOptional.orElseThrow(() -> new IllegalArgumentException("User not found for ID: " + Id));
    }
}
