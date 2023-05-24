/**
 * FileName: GroupService
 * Author: jane
 * Date: 2023/5/23 16:42
 * Description:
 * Version:
 */

package com.sphere.backend.service;

import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.User;
import com.sphere.backend.entity.UserGroup;
import com.sphere.backend.repository.GroupRepo;
import com.sphere.backend.repository.UserGroupRepo;
import com.sphere.backend.service.impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GroupService implements GroupServiceImpl {

    @Autowired
    GroupRepo groupRepo;

    @Autowired
    UserGroupRepo userGroupRepo;

    @Override
    public Group createGroup(String groupName, User owner, String description) {
        Group group = new Group(groupName, owner, description);
        return groupRepo.save(group);
    }

    // TODO: add findByGroupId/GroupName function, 2023/5/23
    // public Group findBy__(){
    //
    // }

    @Override
    public void joinGroup(Group group, User user){
        UserGroup join = new UserGroup(user, group, new Date());
        userGroupRepo.save(join);
        group.setMemberNum(group.getMemberNum() + 1);
    }

    @Override
    public void leaveGroup(Group group, User user){
        UserGroup join = userGroupRepo.deleteByUserAndGroup(group, user);
        group.setMemberNum(group.getMemberNum() - 1);
    }

    @Override
    public List<User> findUsersOfGroup(Group group) {
        List<UserGroup> userGroups = group.getUserGroups();
        List<User> users = new ArrayList<>();
        for (UserGroup userGroup : userGroups) {
            users.add(userGroup.getUser());
        }
        return users;
    }



    @Override
    public List<Group> findAll(){
        return groupRepo.findAll();
    }
}
