package com.adriano.api_rest_client.persistence.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public abstract class BaseRestDAO<T, ID> {

    protected final RestTemplate restTemplate;
    private final String resourcePath;
    private final Class<T> dtoClass;
    private final ParameterizedTypeReference<List<T>> listType;

    protected BaseRestDAO(RestTemplate restTemplate,
                          String resourcePath,
                          Class<T> dtoClass,
                          ParameterizedTypeReference<List<T>> listType) {
        this.restTemplate = restTemplate;
        this.resourcePath = resourcePath;
        this.dtoClass = dtoClass;
        this.listType = listType;
    }

    public List<T> list() {
        ResponseEntity<List<T>> response = restTemplate.exchange(resourcePath, HttpMethod.GET, null, listType);
        return response.getBody();
    }

    public T get(ID id) {
        return restTemplate.getForObject(resourcePath + "/{id}", dtoClass, id);
    }

    public T create(T entity) {
        return restTemplate.postForObject(resourcePath, entity, dtoClass);
    }

    public T update(ID id, T entity) {
        restTemplate.put(resourcePath + "/{id}", entity, id);
        return get(id);
    }

    public void delete(ID id) {
        restTemplate.delete(resourcePath + "/{id}", id);
    }
}
