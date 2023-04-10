/**
 * FileName: UserServiceImpl
 * Author: jane
 * Date: 2023/4/9 19:01
 * Description:
 * Version:
 */
package com.sphere.backend.service.impl;

import com.sphere.backend.entity.User;

public interface UserServiceImpl {
    public User registerUser(User user);
    public boolean isEmailUnique(String email);
}
