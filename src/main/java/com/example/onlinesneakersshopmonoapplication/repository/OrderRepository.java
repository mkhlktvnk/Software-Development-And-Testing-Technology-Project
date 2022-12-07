package com.example.onlinesneakersshopmonoapplication.repository;

import com.example.onlinesneakersshopmonoapplication.entity.Order;
import com.example.onlinesneakersshopmonoapplication.entity.Product;
import com.example.onlinesneakersshopmonoapplication.entity.User;

import java.util.List;

public interface OrderRepository extends CommonRepository<Order> {
    List<Order> findOrdersByUser(User user);

    boolean existsByProductAndUser(Product product, User user);
}
