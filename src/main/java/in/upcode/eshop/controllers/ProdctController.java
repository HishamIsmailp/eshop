package in.upcode.eshop.controllers;

import in.upcode.eshop.models.Product;
import in.upcode.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
@Controller
public class ProdctController {
    @Autowired
    ProductService productService;

//    @GetMapping("products")
//    public List<Product> GetAllProducts(){
//       return productService.GetAllProducts();
//    }
    @RequestMapping("products")
    @ResponseBody
    public List<Product> GetAllProducts(){
    return productService.GetAllProducts();
}

    @RequestMapping("view/products")
    public String displayProducts(Model model){
        List<Product> products=productService.GetAllProducts();
        model.addAttribute("products",products);
        return "products";
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

    @RequestMapping("view/products/{id}")
    public String displayProductDetail(@PathVariable Long id,Model model){
        Optional<Product> product=productService.GetProduct(id);
        model.addAttribute("product",product.get());
        return "productdetail";
    }

    @DeleteMapping("products/{id}")
    public void DeleteProduct(@PathVariable Long id){
        productService.DeleteProduct(id);
    }

}
