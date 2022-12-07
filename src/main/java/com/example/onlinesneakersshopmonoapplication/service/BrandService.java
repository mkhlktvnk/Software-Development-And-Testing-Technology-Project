package com.example.onlinesneakersshopmonoapplication.service;

import com.example.onlinesneakersshopmonoapplication.entity.Brand;
import com.example.onlinesneakersshopmonoapplication.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService extends AbstractService<Brand, BrandRepository> {
    @Autowired
    public BrandService(BrandRepository repository) {
        super(repository);
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
