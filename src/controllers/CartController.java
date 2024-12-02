package controllers;

import entities.Product;
import services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

// Контроллер для управления корзиной клиента:

@Component
public class CartController {

    @Autowired
    private CartService cartService;

    private final Scanner scanner = new Scanner(System.in);

    // Запуск взаимодействия с клиентом для работы с корзиной:

    public void run() {
        while (true) {
            System.out.println("=== Меню корзины ===");
            System.out.println("1 - Добавить продукт в корзину");
            System.out.println("2 - Показать все продукты в корзине");
            System.out.println("3 - Выход из корзины");
            System.out.print("Ваш выбор (Введите цифру): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Введите корректное число.");
                scanner.nextLine(); // Поглощение некорректного ввода.
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Поглощение символа новой строки.

            switch (choice) {
                case 1 -> addProductToCart();
                case 2 -> showCartContents();
                case 3 -> {
                    System.out.println("Выход из корзины.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Повторите.");
            }
        }
    }

    // Метод для добавления продукта в корзину:

    private void addProductToCart() {
        System.out.print("Введите ID продукта: ");
        if (!scanner.hasNextInt()) {
            System.out.println("ID должен быть числом.");
            scanner.nextLine();
            return;
        }
        int id = scanner.nextInt();
        scanner.nextLine(); // Поглощение символа новой строки.

        System.out.print("Введите название продукта: ");
        String name = scanner.nextLine();

        System.out.print("Введите цену продукта: ");
        if (!scanner.hasNextBigDecimal()) {
            System.out.println("Цена должна быть числом.");
            scanner.nextLine();
            return;
        }
        BigDecimal price = scanner.nextBigDecimal();

        Product product = new Product(id, name, price);
        cartService.addProductToCart(product);

        System.out.println("Продукт добавлен в корзину!");
    }

    // Метод для отображения содержимого корзины:

    private void showCartContents() {
        List<Product> cartProducts = cartService.getAllProductsInCart();
        if (cartProducts.isEmpty()) {
            System.out.println("Корзина пуста.");
        } else {
            System.out.println("=== Содержимое корзины ===");
            cartProducts.forEach(System.out::println);
        }
    }
}