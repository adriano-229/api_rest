package com.example.server.domain.mapper;

import com.example.server.domain.dto.BaseDto;
import com.example.server.domain.entity.BaseEntity;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

    E toEntity(D dto);

    void updateEntity(D dto, @MappingTarget E entity);

    D toDto(E entity);

    List<D> toDtoList(List<E> entities);

}
