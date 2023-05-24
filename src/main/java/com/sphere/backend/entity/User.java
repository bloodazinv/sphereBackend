/**
 * FileName: User
 * Author: jane
 * Date: 2023/4/9 18:56
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
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends IdBasedEntity implements Serializable {
    @Column(length = 128, nullable = false, unique = true)
    private String username;
    @Column(length = 128, nullable = false, unique = true)
    private String email;
    @Column(length = 64, nullable = false)
    private String password;

    private String profilePicture;

    private Date registerTime;

    private boolean enabled;
    @Column(length = 128, nullable = false)
    private String displayName;

    @OneToMany(mappedBy = "user")
    private List<UserGroup> userGroups;

    public User(String displayName, String username, String email, String password) {
        this.displayName = displayName;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
