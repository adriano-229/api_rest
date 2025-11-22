package com.adriano.api_rest_client.service;

import com.adriano.api_rest_client.domain.dto.BaseDTO;
import com.adriano.api_rest_client.persistence.rest.BaseRestDAO;

import java.util.List;
import java.util.Optional;

public abstract class RestCrudService<T extends BaseDTO, ID> implements CrudClientService<T, ID> {

    private final BaseRestDAO<T, ID> restDAO;

    protected RestCrudService(BaseRestDAO<T, ID> restDAO) {
        this.restDAO = restDAO;
    }

    @Override
    public List<T> findAll() {
        return restDAO.list();
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(restDAO.get(id));
    }

    @Override
    public T create(T entity) {
        return restDAO.create(entity);
    }

    @Override
    public T update(ID id, T entity) {
        return restDAO.update(id, entity);
    }

    @Override
    public void delete(ID id) {
        restDAO.delete(id);
    }
}

