package com.flat_service.flat_service.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.flat_service.flat_service.models.Flat;

@Mapper(componentModel = "spring")
public interface FlatMapper {

    @Mapping(target = "imagePath", ignore = true)
    @Mapping(target = "flat_id", ignore = true)
    Flat toEntity(FlatsDto flatDto);

}
