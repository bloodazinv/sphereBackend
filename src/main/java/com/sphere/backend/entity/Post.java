/**
 * FileName: Post
 * Author: jane
 * Date: 2023/5/23 19:02
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

@Entity
@Table(name = "posts")
@NoArgsConstructor
@Getter
@Setter
public class Post extends IdBasedEntity implements Serializable {
    @Column(length = 128, nullable = false)
    private String title;
    @Column(length = 10000, nullable = false)
    private String content;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "op_id")
    private User originalPoster;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;



}
