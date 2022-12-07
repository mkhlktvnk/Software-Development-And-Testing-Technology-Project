package com.example.onlinesneakersshopmonoapplication.service;

import com.example.onlinesneakersshopmonoapplication.entity.RoleType;
import com.example.onlinesneakersshopmonoapplication.entity.User;
import com.example.onlinesneakersshopmonoapplication.exception.EntityNotFoundException;
import com.example.onlinesneakersshopmonoapplication.exception.EntityNotUniqueException;
import com.example.onlinesneakersshopmonoapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails saveUser(User user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new EntityNotUniqueException("User with login " + user.getLogin() + " already exists!");
        }
        user.setRoles(Collections.singleton(RoleType.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new EntityNotFoundException("User with username " + username + " has not found!"));
    }
}
