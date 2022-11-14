package in.upcode.eshop.services;

import in.upcode.eshop.models.Product;
import in.upcode.eshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> GetAllProducts() {
      return productRepository.findAll();
    }

    public void PostProduct(Product product) {
        productRepository.save(product);
    }

    public Optional<Product> GetProduct(Long id) {
        return productRepository.findById(id);
    }

    public void DeleteProduct(Long id) {productRepository.deleteById(id);}
    public void UpdateProduct(Long id, Product prod) {
        Optional<Product> product;
        product = productRepository.findById(id);
        if(product.isEmpty()){
            System.out.println("item not found");
            return;
        }
        Product existingProduct=product.get();
        if (prod.getName()!=null){
            existingProduct.setName(prod.getName());
        }
        if (prod.getQuantity() !=0){
            existingProduct.setQuantity(prod.getQuantity());
        }
        productRepository.save(existingProduct);
    }
}
