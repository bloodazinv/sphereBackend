package com.sphere.backend;

import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.User;
import com.sphere.backend.entity.UserGroup;
import com.sphere.backend.repository.GroupRepo;
import com.sphere.backend.repository.UserGroupRepo;
import com.sphere.backend.repository.UserRepo;
import com.sphere.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SphereApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserGroupRepo userGroupRepo;
    @Autowired
    GroupRepo groupRepo;

    //@Test
    void userTest() {
        //userService.registerUser(new User("Sarah", "sarah@mail.com","123456"));
    }

    @Test
    void groupTest(){
        User jane = userRepo.findByUsername("jane");
        Group dogs = new Group("dogs", jane, "this is the first group");
        groupRepo.save(dogs);
        Group gg = groupRepo.findByGroupName("dogs");
        System.out.println(gg.getDescription());
    }

    @Test
    @Transactional
    void usergroupTest(){
        User sarah = userRepo.findByUsername("sarah");
        Group gg = groupRepo.findByGroupName("dogs");
        // UserGroup sarahDogs = new UserGroup(sarah, gg, new Date());
        // userGroupRepo.save(sarahDogs);
        List<UserGroup> dogsmembers = gg.getUserGroups();
        for (UserGroup user: dogsmembers) {
            System.out.println(user.getUser().getUsername());
        }
    }

    @Test
    void userServiceTest(){
        User user = new User("momo","shkad","shkad@mail.com", "123456");
        userService.registerUser(user);
    }




}
