/**
 * FileName: GroupService
 * Author: jane
 * Date: 2023/5/23 16:42
 * Description:
 * Version:
 */

package com.sphere.backend.service;

import com.sphere.backend.convert.GroupConvert;
import com.sphere.backend.convert.UserConvert;
import com.sphere.backend.dto.GroupDto;
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

// TODO: consider the class of parameter
//user? userdto?
//group? groupdto?
@Service
@Transactional
public class GroupService implements GroupServiceImpl {

    @Autowired
    GroupRepo groupRepo;

    @Autowired
    UserGroupRepo userGroupRepo;

    // TODO: consider the class of parameter
    //user? userdto?
    @Override
    public GroupDto createGroup(String groupName, User owner, String description) {
        Group group = new Group(groupName, owner, description);
        group = groupRepo.save(group);
        GroupDto groupDto = GroupConvert.INSTANCE.po2dto(group);
        groupDto.setOwner(UserConvert.INSTANCE.po2dto(group.getOwner()));
        return groupDto;
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
    public List<GroupDto> findAll(){
        List<Group> groups = groupRepo.findAll();
        List<GroupDto> groupDtos = new ArrayList<>();
        for (Group group : groups) {
            GroupDto groupDto = GroupConvert.INSTANCE.po2dto(group);
            groupDto.setOwner(UserConvert.INSTANCE.po2dto(group.getOwner()));
            groupDtos.add(groupDto);
        }
        return groupDtos;
    }
}
