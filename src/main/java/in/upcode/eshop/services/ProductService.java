package in.upcode.eshop.services;

import in.upcode.eshop.models.Product;
import in.upcode.eshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public void DeleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void UpdateProduct(Long id, String name, String image, int quantity) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return;
        }
        Product existingProduct = product.get();
        if (name != null) {
            existingProduct.setName(name);
        }
        if (quantity != 0) {
            existingProduct.setQuantity(quantity);
        }
        if (image != null) {
            existingProduct.setImage(image);
        }
        productRepository.save(existingProduct);
    }
}
