/**
 * FileName: PostDto
 * Author: jane
 * Date: 2023/5/26 16:34
 * Description:
 * Version:
 */

package com.sphere.backend.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Date createdDate;
    private GroupDto group;
    private UserDto originalPoster;
}
