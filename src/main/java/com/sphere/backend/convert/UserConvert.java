/**
 * FileName: UserConvert
 * Author: jane
 * Date: 2023/5/26 16:38
 * Description:
 * Version:
 */

package com.sphere.backend.convert;

import com.sphere.backend.dto.UserDto;
import com.sphere.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "displayName", target = "displayName"),
            @Mapping(source = "profilePicture", target = "profilePicture")
    })
    UserDto po2dto(User user);
}
