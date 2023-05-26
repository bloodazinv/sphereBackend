/**
 * FileName: UserServiceImpl
 * Author: jane
 * Date: 2023/4/9 19:01
 * Description:
 * Version:
 */
package com.sphere.backend.service.impl;

import com.sphere.backend.dto.GroupDto;
import com.sphere.backend.dto.UserDto;
import com.sphere.backend.entity.User;

import java.util.List;

public interface UserServiceImpl {
    public UserDto registerUser(User user);
    public boolean isEmailUnique(String email);
    public boolean isUsernameUnique(String username);
    public List<GroupDto> findGroupsOfUser(String username);
    public User findById(Long Id);
    public User findByUsername(String username);
}
