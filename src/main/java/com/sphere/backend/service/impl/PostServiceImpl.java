/**
 * FileName: PostServiceImpl
 * Author: jane
 * Date: 2023/5/23 19:21
 * Description:
 * Version:
 */
package com.sphere.backend.service.impl;

import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.Post;
import com.sphere.backend.entity.User;

import java.util.List;

public interface PostServiceImpl {
    List<Post> getPosts(List<Group> userGroups);

    public List<Post> getPostsByGroup(Group group);
}
