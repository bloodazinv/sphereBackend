/**
 * FileName: PostServiceImpl
 * Author: jane
 * Date: 2023/5/23 19:21
 * Description:
 * Version:
 */
package com.sphere.backend.service.impl;

import com.sphere.backend.dto.GroupDto;
import com.sphere.backend.dto.PostDto;
import com.sphere.backend.entity.Group;

import java.util.List;

public interface PostServiceImpl {
    List<PostDto> getPosts(List<GroupDto> userGroups);

    public List<PostDto> getPostsByGroupId(Long groupId);
}
