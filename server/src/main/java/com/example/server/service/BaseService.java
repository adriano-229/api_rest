package com.example.server.service;

import com.example.server.domain.dto.BaseDto;
import com.example.server.domain.entity.BaseEntity;
import com.example.server.domain.mapper.BaseMapper;
import com.example.server.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<E extends BaseEntity, D extends BaseDto, ID extends Serializable>
        implements CrudHooks<E, D, ID> {

    protected final BaseRepository<E, ID> baseRepository;
    protected final BaseMapper<E, D> baseMapper;

    public BaseService(BaseRepository<E, ID> baseRepository,
                       BaseMapper<E, D> baseMapper) {
        this.baseRepository = baseRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional(readOnly = true)
    public List<D> readAll() {
        List<E> entities = baseRepository.findAll();
        return baseMapper.toDtoList(entities);
    }

    @Transactional(readOnly = true)
    public Optional<D> readById(ID id) {
        beforeRead(id);
        Optional<E> entity = baseRepository.findById(id);
        entity.ifPresent(this::afterRead);
        return entity.map(baseMapper::toDto);
    }

    @Transactional
    public D create(D dto) {
        beforeCreate(dto);
        E entity = baseMapper.toEntity(dto);
        E saved = baseRepository.save(entity);
        afterCreate(saved);
        return baseMapper.toDto(saved);
    }

    @Transactional
    public Optional<D> update(ID id, D dto) {
        beforeUpdate(id, dto);
        return baseRepository.findById(id).map(existing -> {
            E updatedEntity = baseMapper.toEntity(dto);
            updatedEntity.setId(existing.getId());
            E saved = baseRepository.save(updatedEntity);
            afterUpdate(saved);
            return baseMapper.toDto(saved);
        });
    }

    @Transactional
    public void deleteById(ID id) {
        beforeDelete(id);
        baseRepository.findById(id).ifPresent(entity -> {
            baseRepository.delete(entity);
            afterDelete(entity);
        });
    }
}
