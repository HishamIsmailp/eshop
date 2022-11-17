package in.upcode.eshop.controllers;

import in.upcode.eshop.models.Product;
import in.upcode.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProdctController {
    @Autowired
    ProductService productService;

    @RequestMapping("products")
    @ResponseBody
    public List<Product> GetAllProducts() {
        return productService.GetAllProducts();
    }

    @RequestMapping("view/products")
    public String displayProducts(Model model) {
        List<Product> products = productService.GetAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("view/products/{id}")
    public String displayProductDetail(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.GetProduct(id);
        model.addAttribute("product", product.get());
        return "productdetail";
    }

    @RequestMapping("view/products/new")
    public String displayCreateForm(Model model) {
        Product product = new Product();
        model.addAttribute(product);
        return "newproduct";
    }

    @RequestMapping(value = "view/products/edit/{id}", method = RequestMethod.GET)
    public String displayEditForm(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.GetProduct(id);
        model.addAttribute(product.get());
        return "editproduct";
    }

    @RequestMapping(value = "view/products/create", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") Product product) {
        productService.PostProduct(product);
        return "redirect:/view/products";
    }

    @RequestMapping("view/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.DeleteProduct(id);
        return "redirect:/view/products";
    }

    @RequestMapping("view/products/update")
    public String updateProduct(@RequestParam(required = false) Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String image, @RequestParam(required = false) int quantity) {
        productService.UpdateProduct(id, name, image, quantity);
        return "redirect:/view/products";
    }
}
