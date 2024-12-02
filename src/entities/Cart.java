package entities;

import java.math.BigDecimal;  // Импортируем BigDecimal.
import java.util.ArrayList;
import java.util.List;

// Сущность - "Корзина":
public class Cart {

    private List<Product> products = new ArrayList<>();

    // Метод для добавления товара в корзину:
    public void addProduct(Product product) {
        products.add(product);
    }

    // Метод для удаления товара из корзины:
    public void removeProduct(Product product) {
        products.remove(product);
    }

    // Метод для подсчета общей суммы в корзине:
    public BigDecimal calculateTotalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : products) {
            total = total.add(product.getPrice());
        }
        return total;
    }

    // Геттеры:
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                ", totalPrice=" + calculateTotalPrice() +
                '}';
    }
}