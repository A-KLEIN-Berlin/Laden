package services;

import entities.Cart;
import entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Сервис для работы с корзиной клиента:

@Service
public class CartService {

    private Cart cart = new Cart(); // Единая корзина для клиента.

    // Добавление продукта в корзину. @param product продукт для добавления.

    public void addProductToCart(Product product) {
        cart.getProducts().add(product);
    }

    // Получение всех продуктов в корзине. @return список продуктов в корзине.

    public List<Product> getAllProductsInCart() {
        return new ArrayList<>(cart.getProducts());
    }
}