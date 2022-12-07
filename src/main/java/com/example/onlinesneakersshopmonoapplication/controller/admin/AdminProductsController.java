package com.example.onlinesneakersshopmonoapplication.controller.admin;

import com.example.onlinesneakersshopmonoapplication.entity.Brand;
import com.example.onlinesneakersshopmonoapplication.entity.Product;
import com.example.onlinesneakersshopmonoapplication.exception.EntityNotFoundException;
import com.example.onlinesneakersshopmonoapplication.exception.UnableToDeleteEntityException;
import com.example.onlinesneakersshopmonoapplication.service.BrandService;
import com.example.onlinesneakersshopmonoapplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class AdminProductsController {
    private final ProductService productService;
    private final BrandService brandService;

    @GetMapping("/admin/add_product")
    public ModelAndView getProductAddForm() {
        ModelAndView modelAndView = new ModelAndView("product/add");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("brands", brandService.findAll());
        return modelAndView;
    }

    @PostMapping(value = "/admin/add_product")
    public RedirectView addProduct(@ModelAttribute("product") Product product,
                                   @RequestParam("brandId") Long brandId) {
        Brand brand = brandService.findById(brandId)
                .orElseThrow(() -> new EntityNotFoundException("Brand was not found!"));
        product.setBrand(brand);
        productService.save(product);
        return new RedirectView("/products");
    }

    @PostMapping("/admin/delete_product/{productId}")
    public RedirectView deleteProduct(@PathVariable Long productId) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product was not found!"));
        try {
            productService.delete(product);
        } catch (Exception e) {
            throw new UnableToDeleteEntityException("Unable to delete product "
                    + product.getName() + " because there is order for it!");
        }
        return new RedirectView("/products");
    }
}
