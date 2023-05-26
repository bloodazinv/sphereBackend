/**
 * FileName: HomeController
 * Author: jane
 * Date: 2023/5/23 17:13
 * Description:
 * Version:
 */

package com.sphere.backend.controller;

import com.sphere.backend.dto.GroupDto;
import com.sphere.backend.dto.PostDto;
import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.Post;
import com.sphere.backend.entity.User;
import com.sphere.backend.payload.HomeResponse;
import com.sphere.backend.service.GroupService;
import com.sphere.backend.service.PostService;
import com.sphere.backend.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "home page")
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;
    @Autowired
    PostService postService;

    @GetMapping("/home")
    public HomeResponse getUserGroupsAndPost() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<GroupDto> userGroups = userService.findGroupsOfUser(username);
        if(userGroups.size() == 0){
            userGroups = groupService.findAll();
        }
        List<PostDto> userPosts = postService.getPosts(userGroups);
        return new HomeResponse(userGroups, userPosts);
    }





}
