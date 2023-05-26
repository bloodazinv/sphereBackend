/**
 * FileName: GroupConvert
 * Author: jane
 * Date: 2023/5/26 16:44
 * Description:
 * Version:
 */

package com.sphere.backend.convert;

import com.sphere.backend.dto.GroupDto;
import com.sphere.backend.dto.UserDto;
import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupConvert {
    GroupConvert INSTANCE = Mappers.getMapper(GroupConvert.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "groupName", target = "groupName"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "profilePic", target = "profilePic"),
            @Mapping(source = "createdTime", target = "createdTime"),
            @Mapping(source = "memberNum", target = "memberNum")
    })
    GroupDto po2dto(Group group);
}
