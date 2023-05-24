/**
 * FileName: PostService
 * Author: jane
 * Date: 2023/5/23 19:22
 * Description:
 * Version:
 */

package com.sphere.backend.service;

import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.Post;
import com.sphere.backend.entity.User;
import com.sphere.backend.repository.PostRepo;
import com.sphere.backend.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class PostService implements PostServiceImpl {
    @Autowired
    PostRepo postRepo;

    @Override
    public List<Post> getPosts(List<Group> userGroups) {
        List<Post> posts = new ArrayList<>();
        for (Group userGroup : userGroups) {
            posts.addAll(getPostsByGroup(userGroup));
        }
        Collections.sort(posts, Comparator.comparing(Post::getCreatedDate).reversed());
        return posts;
    }

    @Override
    public List<Post> getPostsByGroup(Group group){
        return postRepo.findByGroup(group);
    }




}
