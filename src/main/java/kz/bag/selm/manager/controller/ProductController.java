package kz.bag.selm.manager.controller;

import kz.bag.selm.manager.controller.payload.UpdateProductPayLoad;
import kz.bag.selm.manager.entity.Product;
import kz.bag.selm.manager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products/{productId:\\d+}")
public class ProductController {

    private final ProductService productService;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId) {
        return this.productService.findProduct(productId).orElseThrow();
    }

    @GetMapping
    public String getProduct() {
        return "catalogue/products/product";
    }

    @GetMapping("edit")
    public String getProductEditPage() {
        return "catalogue/products/edit";
    }

    @PostMapping("edit")
    public String updateProduct(@ModelAttribute("product") Product product, UpdateProductPayLoad payLoad) {
        this.productService.updateProduct(product.getId(), payLoad.title(), payLoad.details());
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }
}
