package com.example.onlinesneakersshopmonoapplication.service;

import com.example.onlinesneakersshopmonoapplication.entity.Product;
import com.example.onlinesneakersshopmonoapplication.entity.SeasonType;
import com.example.onlinesneakersshopmonoapplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends AbstractService<Product, ProductRepository> {
    @Autowired
    public ProductService(ProductRepository repository) {
        super(repository);
    }

    public List<Product> getProductsByBrandIdAndSeasonType(Long brandId, SeasonType seasonType) {
        return repository.findProductsByBrandIdAndSeasonType(brandId, seasonType);
    }

}
