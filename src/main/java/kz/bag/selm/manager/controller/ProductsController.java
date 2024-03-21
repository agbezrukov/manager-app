package kz.bag.selm.manager.controller;

import kz.bag.selm.manager.controller.payload.NewProductPayLoad;
import kz.bag.selm.manager.entity.Product;
import kz.bag.selm.manager.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping("list")
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductPage() {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayLoad payLoad) {
        Product product = this.productService.createProduct(payLoad.title(), payLoad.details());
        return "redirect:/catalogue/products/%d".formatted(product.getId());
    }
}
