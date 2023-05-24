/**
 * FileName: GroupRepo
 * Author: jane
 * Date: 2023/5/23 15:13
 * Description:
 * Version:
 */
package com.sphere.backend.repository;

import com.sphere.backend.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepo extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g WHERE g.groupName = ?1")
    public Group findByGroupName(String groupName);


}
