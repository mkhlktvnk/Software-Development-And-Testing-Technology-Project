package com.example.onlinesneakersshopmonoapplication.repository;

import com.example.onlinesneakersshopmonoapplication.entity.Brand;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CommonRepository<Brand> {
    boolean existsByName(String name);
    boolean existsById(Long id);
}
