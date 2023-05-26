/**
 * FileName: UserDto
 * Author: jane
 * Date: 2023/5/26 16:21
 * Description:
 * Version:
 */

package com.sphere.backend.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDto {
    private Long id;
    private String displayName;
    private String profilePicture;
    private String username;
}
