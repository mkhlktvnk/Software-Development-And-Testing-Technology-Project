package com.example.onlinesneakersshopmonoapplication.service;

import com.example.onlinesneakersshopmonoapplication.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface CommonService<E extends AbstractEntity> {
    E save(E entity);
    Optional<E> findById(Long id);
    List<E> findAll();
    void delete(E entity);
    void deleteById(Long id);
}
