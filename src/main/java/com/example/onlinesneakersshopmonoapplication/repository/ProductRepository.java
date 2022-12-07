package com.example.onlinesneakersshopmonoapplication.repository;

import com.example.onlinesneakersshopmonoapplication.entity.Product;
import com.example.onlinesneakersshopmonoapplication.entity.SeasonType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CommonRepository<Product> {
    List<Product> findProductsByBrandIdAndSeasonType(Long brandId, SeasonType seasonType);
}
