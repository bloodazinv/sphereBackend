/**
 * FileName: PostService
 * Author: jane
 * Date: 2023/5/23 19:22
 * Description:
 * Version:
 */

package com.sphere.backend.service;

import com.sphere.backend.convert.GroupConvert;
import com.sphere.backend.convert.PostConvert;
import com.sphere.backend.convert.UserConvert;
import com.sphere.backend.dto.GroupDto;
import com.sphere.backend.dto.PostDto;
import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.Post;
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
    public List<PostDto> getPosts(List<GroupDto> userGroups) {
        List<PostDto> posts = new ArrayList<>();
        for (GroupDto userGroup : userGroups) {
            posts.addAll(getPostsByGroupId(userGroup.getId()));
        }
        Collections.sort(posts, Comparator.comparing(PostDto::getCreatedDate).reversed());

        return posts;
    }

    @Override
    public List<PostDto> getPostsByGroupId(Long groupId){
        List<Post> posts = postRepo.findByGroup_Id(groupId);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            PostDto postDto = PostConvert.INSTANCE.po2dto(post);
            postDto.setGroup(GroupConvert.INSTANCE.po2dto(post.getGroup()));
            postDto.setOriginalPoster(UserConvert.INSTANCE.po2dto(post.getOriginalPoster()));
            postDtos.add(postDto);
        }
        return postDtos;
    }




}
