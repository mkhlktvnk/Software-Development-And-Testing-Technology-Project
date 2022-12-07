package com.example.onlinesneakersshopmonoapplication.controller;

import com.example.onlinesneakersshopmonoapplication.entity.Product;
import com.example.onlinesneakersshopmonoapplication.entity.SeasonType;
import com.example.onlinesneakersshopmonoapplication.entity.User;
import com.example.onlinesneakersshopmonoapplication.exception.EntityNotFoundException;
import com.example.onlinesneakersshopmonoapplication.service.BrandService;
import com.example.onlinesneakersshopmonoapplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final BrandService brandService;

    @GetMapping("/products")
    public ModelAndView getProducts() {
        ModelAndView modelAndView = new ModelAndView("product/index");
        modelAndView.addObject("brands", brandService.findAll());
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @GetMapping("/find_products")
    public ModelAndView getProductsByBrandIdAndSeasonType(@ModelAttribute("brandId") Long brandId,
                                                          @ModelAttribute("seasonType") SeasonType seasonType) {
        if (!brandService.existsById(brandId)) {
            throw new EntityNotFoundException("Brand not found!");
        }
        ModelAndView modelAndView = new ModelAndView("product/index");
        modelAndView.addObject("brands", brandService.findAll());
        modelAndView.addObject("products",
                productService.getProductsByBrandIdAndSeasonType(brandId, seasonType));
        return modelAndView;
    }

    @GetMapping("/products/{productId}")
    public ModelAndView getProductById(@PathVariable Long productId, @AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("product/show");
        Product product = productService.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found!"));
        modelAndView.addObject("product", product);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
