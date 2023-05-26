/**
 * FileName: PostRepo
 * Author: jane
 * Date: 2023/5/23 19:20
 * Description:
 * Version:
 */
package com.sphere.backend.repository;

import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.Post;
import com.sphere.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {


    List<Post> findByGroup_Id(Long groupId);
}
