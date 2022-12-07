package com.example.onlinesneakersshopmonoapplication.repository;

import com.example.onlinesneakersshopmonoapplication.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CommonRepository<User> {
    Optional<User> findByLogin(String login);

    boolean existsByLogin(String login);
}
