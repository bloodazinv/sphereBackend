/**
 * FileName: GroupServiceImpl
 * Author: jane
 * Date: 2023/5/23 16:41
 * Description:
 * Version:
 */
package com.sphere.backend.service.impl;

import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.User;

import java.util.List;

public interface GroupServiceImpl {
    public List<User> findUsersOfGroup(Group group);
    public Group createGroup(String groupName, User owner, String description);
    public void joinGroup(Group group, User user);
    public void leaveGroup(Group group, User user);
    public List<Group> findAll();
}
