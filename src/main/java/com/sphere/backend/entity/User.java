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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends IdBasedEntity implements Serializable {
    @Column(length = 128, nullable = false)
    private String username;
    @Column(length = 128, nullable = false, unique = true)
    private String email;
    @Column(length = 64, nullable = false)
    private String password;

    private String profilePicture;

    private Date registerTime;

    private boolean enabled;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
