/**
 * FileName: UserGroup
 * Author: jane
 * Date: 2023/5/23 14:56
 * Description:
 * Version:
 */

package com.sphere.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user_group")
@NoArgsConstructor
@Getter
@Setter
public class UserGroup extends IdBasedEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "joined_time")
    private Date joinedTime;

    public UserGroup(User user, Group group, Date joinedTime) {
        this.user = user;
        this.group = group;
        this.joinedTime = joinedTime;
    }
}
