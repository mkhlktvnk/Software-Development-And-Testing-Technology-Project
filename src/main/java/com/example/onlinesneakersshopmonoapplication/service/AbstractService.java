package com.example.onlinesneakersshopmonoapplication.service;


import com.example.onlinesneakersshopmonoapplication.entity.AbstractEntity;
import com.example.onlinesneakersshopmonoapplication.repository.CommonRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>> implements CommonService<E> {
    protected final R repository;

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return (List<E>) repository.findAll();
    }

    @Override
    public void delete(E entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
