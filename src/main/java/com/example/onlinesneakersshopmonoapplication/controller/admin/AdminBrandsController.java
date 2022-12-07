package com.example.onlinesneakersshopmonoapplication.controller.admin;

import com.example.onlinesneakersshopmonoapplication.entity.Brand;
import com.example.onlinesneakersshopmonoapplication.exception.EntityNotUniqueException;
import com.example.onlinesneakersshopmonoapplication.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class AdminBrandsController {
    private final BrandService brandService;

    @GetMapping("/admin/add_brand")
    public ModelAndView getBrandAddForm() {
        ModelAndView modelAndView = new ModelAndView("brand/add");
        modelAndView.addObject("brand", new Brand());
        return modelAndView;
    }

    @PostMapping("/admin/add_brand")
    public RedirectView addBrand(@ModelAttribute Brand brand) {
        if (brandService.existsByName(brand.getName())) {
            throw new EntityNotUniqueException("Brand with name " + brand.getName() + " already exists!");
        }
        brandService.save(brand);
        return new RedirectView("/products");
    }
}
