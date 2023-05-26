/**
 * FileName: HomeResponse
 * Author: jane
 * Date: 2023/5/23 18:58
 * Description:
 * Version:
 */

package com.sphere.backend.payload;

import com.sphere.backend.dto.GroupDto;
import com.sphere.backend.dto.PostDto;
import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.Post;

import java.util.List;

public class HomeResponse {
    private List<GroupDto> groups;
    private List<PostDto> posts;

    public HomeResponse(List<GroupDto> groups, List<PostDto> posts) {
        this.groups = groups;
        this.posts = posts;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }

    public List<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDto> groups) {
        this.groups = groups;
    }
}
