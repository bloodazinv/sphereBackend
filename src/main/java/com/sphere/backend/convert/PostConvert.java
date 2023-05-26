/**
 * FileName: PostDto
 * Author: jane
 * Date: 2023/5/26 16:47
 * Description:
 * Version:
 */
package com.sphere.backend.convert;

import com.sphere.backend.dto.GroupDto;
import com.sphere.backend.dto.PostDto;
import com.sphere.backend.entity.Group;
import com.sphere.backend.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostConvert {
    PostConvert INSTANCE = Mappers.getMapper(PostConvert.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "content", target = "content"),
            @Mapping(source = "createdDate", target = "createdDate")
    })
    PostDto po2dto(Post post);
}
