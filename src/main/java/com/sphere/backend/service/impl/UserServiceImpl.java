/**
 * FileName: UserServiceImpl
 * Author: jane
 * Date: 2023/4/9 19:01
 * Description:
 * Version:
 */
package com.sphere.backend.service.impl;

import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.User;

import java.util.List;

public interface UserServiceImpl {
    public User registerUser(User user);
    public boolean isEmailUnique(String email);
    public boolean isUsernameUnique(String username);
    public List<Group> findGroupsOfUser(String username);
    public User findById(Long Id);
    public User findByUsername(String username);
}
