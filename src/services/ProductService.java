package services;

import entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Метод для добавления нового продукта:
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    // Метод для получения всех продуктов:
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Метод для поиска продукта по ID:
    public Optional<Product> getProductById(int id) {
        return productRepository.findAll().stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }
}