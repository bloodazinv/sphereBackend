/**
 * FileName: GroupDto
 * Author: jane
 * Date: 2023/5/26 16:09
 * Description:
 * Version:
 */

package com.sphere.backend.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class GroupDto {
    private Long id;
    private String groupName;
    private String description;
    private String profilePic;
    private Date createdTime;
    private int memberNum;
    private UserDto owner;
}
