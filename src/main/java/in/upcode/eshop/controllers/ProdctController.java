package in.upcode.eshop.controllers;

import in.upcode.eshop.models.Product;
import in.upcode.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProdctController {
    @Autowired
    ProductService productService;

    @GetMapping("products")
    public List<Product> GetAllProducts(){
       return productService.GetAllProducts();
    }

    @PostMapping("products")
    public void PostProduct(@RequestBody Product product){
        productService.PostProduct(product);
    }


    @PutMapping("products/{id}")
    public void UpdateProduct(@PathVariable Long id,@RequestBody Product product){
        productService.UpdateProduct(id,product);
    }

    @GetMapping("products/{id}")
    public Optional<Product> GetAllProducts(@PathVariable Long id){
        return productService.GetProduct(id);
    }

    @DeleteMapping("products/{id}")
    public void DeleteProduct(@PathVariable Long id){
        productService.DeleteProduct(id);
    }

}
