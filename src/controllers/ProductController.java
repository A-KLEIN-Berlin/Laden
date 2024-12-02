package controllers;

import entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.ProductService;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ProductController {

    @Autowired
    private ProductService productService;

    private final Scanner scanner = new Scanner(System.in);

    // Запуск взаимодействия с пользователем:
    public void run() {
        while (true) {
            // Главное меню:
            System.out.println("1 - Добавить продукт");
            System.out.println("2 - Показать все продукты");
            System.out.println("3 - Найти продукт по ID");
            System.out.println("4 - Выход");
            System.out.print("Ваш выбор (введите цифру): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Поглощение символа новой строки.

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> showAllProducts();
                case 3 -> findProductById();
                case 4 -> {
                    System.out.println("Выход из магазина.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Повторите.");
            }
        }
    }

    private void addProduct() {
        System.out.println("\n=== Добавление нового продукта ===");
        System.out.print("Введите ID продукта: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите название продукта: ");
        String name = scanner.nextLine();

        System.out.print("Введите цену продукта: ");
        double price = scanner.nextDouble();
        BigDecimal bigDecimalPrice = BigDecimal.valueOf(price); // Преобразуем double в BigDecimal.

        Product product = new Product(id, name, bigDecimalPrice);
        productService.addProduct(product);

        System.out.println("Продукт добавлен!\n");
    }

    private void showAllProducts() {
        System.out.println("\n=== Список всех продуктов ===");
        productService.getAllProducts().forEach(System.out::println);
        System.out.println();
    }

    private void findProductById() {
        System.out.println("\n=== Поиск продукта по ID ===");
        System.out.print("Введите ID продукта для поиска: ");
        int id = scanner.nextInt();
        productService.getProductById(id)
                .ifPresentOrElse(
                        product -> System.out.println("Продукт найден: " + product + "\n"),
                        () -> System.out.println("Продукт с таким ID не найден.\n")
                );
    }
}