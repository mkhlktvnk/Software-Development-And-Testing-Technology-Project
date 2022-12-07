package com.example.onlinesneakersshopmonoapplication.service;

import com.example.onlinesneakersshopmonoapplication.entity.Order;
import com.example.onlinesneakersshopmonoapplication.entity.Product;
import com.example.onlinesneakersshopmonoapplication.entity.User;
import com.example.onlinesneakersshopmonoapplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends AbstractService<Order, OrderRepository> {
    @Autowired
    public OrderService(OrderRepository repository) {
        super(repository);
    }

    public List<Order> getUserOrders(User user) {
        return repository.findOrdersByUser(user);
    }

    public boolean existsByProductAndUser(Product product, User user) {
        return repository.existsByProductAndUser(product, user);
    }
}
