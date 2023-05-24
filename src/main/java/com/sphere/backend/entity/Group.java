/**
 * FileName: Group
 * Author: jane
 * Date: 2023/5/23 12:03
 * Description:
 * Version:
 */

package com.sphere.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@Getter
@Setter
public class Group extends IdBasedEntity implements Serializable {
    @Column(length = 128, nullable = false, unique = true)
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(length = 1000, nullable = true, unique = false)
    private String description;

    @Column(length = 200, nullable = true, unique = false)
    private String profilePic;

    @Column(nullable = false)
    private int memberNum;

    private Date createdTime;

    @OneToMany(mappedBy = "group")
    private List<UserGroup> userGroups;

    public Group(String groupName, User owner, String description){
        this.groupName = groupName;
        this.owner = owner;
        this.description = description;
        this.createdTime = new Date();
    }



}
