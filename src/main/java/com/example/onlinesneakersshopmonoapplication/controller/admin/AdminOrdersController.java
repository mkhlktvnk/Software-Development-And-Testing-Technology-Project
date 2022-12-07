package com.example.onlinesneakersshopmonoapplication.controller.admin;

import com.example.onlinesneakersshopmonoapplication.entity.Order;
import com.example.onlinesneakersshopmonoapplication.exception.EntityNotFoundException;
import com.example.onlinesneakersshopmonoapplication.repository.ProductRepository;
import com.example.onlinesneakersshopmonoapplication.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AdminOrdersController {
    private final OrderService orderService;
    private final ProductRepository productRepository;

    @GetMapping("/admin/active_orders")
    public ModelAndView getActiveOrders() {
        ModelAndView modelAndView = new ModelAndView("order/index");
        modelAndView.addObject("orders", orderService.findAll());
        return modelAndView;
    }

    @PostMapping("/admin/submit_order")
    public ModelAndView submitOrder(@ModelAttribute("orderId") Long orderId) {
        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Error! Order was not found!"));
        productRepository.delete(order.getProduct());
        orderService.delete(order);
        ModelAndView modelAndView = new ModelAndView("message/message");
        modelAndView.addObject("message", "Order was successfully submitted!");
        return modelAndView;
    }

    @PostMapping("/admin/decline_order")
    public ModelAndView declineOrder(@ModelAttribute("orderId") Long orderId) {
        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Error! Order was not found!"));
        orderService.delete(order);
        ModelAndView modelAndView = new ModelAndView("message/message");
        modelAndView.addObject("message", "Order was successfully declined!");
        return modelAndView;
    }
}
