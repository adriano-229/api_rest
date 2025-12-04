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
        return baseRepository.findById(id)
                .map(entity -> {
                    afterRead(entity);
                    return baseMapper.toDto(entity);
                });
    }


    @Transactional
    public D create(D dto) {
        E entity = baseMapper.toEntity(dto);
        beforeCreate(dto, entity);
        E saved = baseRepository.save(entity);
        afterCreate(saved);
        return baseMapper.toDto(saved);
    }


    @Transactional
    public Optional<D> update(ID id, D dto) {
        return baseRepository.findById(id).map(existing -> {
            beforeUpdate(id, dto, existing);
            baseMapper.updateEntity(dto, existing);
            E saved = baseRepository.save(existing);
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
