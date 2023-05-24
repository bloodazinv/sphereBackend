/**
 * FileName: HomeResponse
 * Author: jane
 * Date: 2023/5/23 18:58
 * Description:
 * Version:
 */

package com.sphere.backend.payload;

import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.Post;

import java.util.List;

public class HomeResponse {
    private List<Group> groups;
    private List<Post> posts;

    public HomeResponse(List<Group> groups, List<Post> posts) {
        this.groups = groups;
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
