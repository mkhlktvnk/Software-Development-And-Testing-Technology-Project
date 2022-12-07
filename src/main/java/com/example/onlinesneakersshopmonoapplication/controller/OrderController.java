package com.example.onlinesneakersshopmonoapplication.controller;

import com.example.onlinesneakersshopmonoapplication.entity.Order;
import com.example.onlinesneakersshopmonoapplication.entity.Product;
import com.example.onlinesneakersshopmonoapplication.entity.User;
import com.example.onlinesneakersshopmonoapplication.exception.EntityNotFoundException;
import com.example.onlinesneakersshopmonoapplication.exception.EntityNotUniqueException;
import com.example.onlinesneakersshopmonoapplication.service.OrderService;
import com.example.onlinesneakersshopmonoapplication.service.ProductService;
import com.example.onlinesneakersshopmonoapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/orders")
    public ModelAndView getUserOrders(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("order/index");
        modelAndView.addObject("orders", orderService.getUserOrders(user));
        return modelAndView;
    }

    @PostMapping("/orders/add")
    public ModelAndView makeOrder(
            @ModelAttribute("productId") Long productId,
            @ModelAttribute("username") String username) {
        Order order = new Order();
        Product product = productService.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found!"));
        User user = (User) userService.loadUserByUsername(username);
        if (orderService.existsByProductAndUser(product, user)) {
            throw new EntityNotUniqueException("This item has already been added to your orders!");
        }
        order.setUser(user);
        order.setProduct(product);
        orderService.save(order);
        ModelAndView modelAndView = new ModelAndView("message/message");
        modelAndView.addObject("message", "Your order has been successfully placed! You will be contacted soon.");
        return modelAndView;
    }
}
