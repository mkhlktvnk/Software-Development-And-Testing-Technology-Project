package com.example.onlinesneakersshopmonoapplication.controller;

import com.example.onlinesneakersshopmonoapplication.entity.User;
import com.example.onlinesneakersshopmonoapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("auth/registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/registration")
    public RedirectView addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return new RedirectView("/");
    }
}
