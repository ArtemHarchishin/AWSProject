package com.akharchyshyn.aws.mapper;

import com.akharchyshyn.aws.dto.ImageInfoDTO;
import com.akharchyshyn.aws.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageInfoDTO toImageInfoDTO(Image image);

}
