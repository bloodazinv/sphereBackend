/**
 * FileName: UserGroupRepo
 * Author: jane
 * Date: 2023/5/23 15:38
 * Description:
 * Version:
 */
package com.sphere.backend.repository;

import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.User;
import com.sphere.backend.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepo extends JpaRepository<UserGroup, Long> {

    List<UserGroup> findByGroup(Group group);

    List<UserGroup> findByUser(User user);

    UserGroup deleteByUserAndGroup(Group group, User user);
}
