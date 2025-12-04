package com.example.server.service;

import com.example.server.domain.dto.BaseDto;
import com.example.server.domain.entity.BaseEntity;

import java.io.Serializable;
public interface CrudHooks<E extends BaseEntity, D extends BaseDto, ID extends Serializable> {

    default void beforeCreate(D dto, E entity) {
    }

    default void afterCreate(E entity) {
    }

    default void beforeRead(ID id) {
    }

    default void afterRead(E entity) {
    }

    default void beforeUpdate(ID id, D dto, E entity) {
    }

    default void afterUpdate(E entity) {
    }

    default void beforeDelete(ID id) {
    }

    default void afterDelete(E entity) {
    }
}
