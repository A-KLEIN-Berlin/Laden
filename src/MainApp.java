import config.AppConfig;
import controllers.ProductController;
import controllers.CartController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            // Получаем контроллеры:
            ProductController productController = context.getBean(ProductController.class);
            CartController cartController = context.getBean(CartController.class);

            Scanner scanner = new Scanner(System.in);

            // Главное меню:
            while (true) {
                System.out.println("\n=====================================");
                System.out.println("         === Главное меню ===         ");
                System.out.println("=====================================");
                System.out.println("Введите цифру 1 для работы с продуктами в магазине.");
                System.out.println("Введите цифру 2 для работы с корзиной клиента.");
                System.out.println("Введите цифру 0 для выхода из программы.");
                System.out.print("Ожидаем Ваш выбор: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Поглощение символа новой строки.

                switch (choice) {
                    case 1 -> {
                        System.out.println("\n=====================================");
                        System.out.println("         === Продуктовый магазин ===         ");
                        System.out.println("=====================================");
                        productController.run(); // Переход к работе с продуктами.
                    }
                    case 2 -> {
                        System.out.println("\n=====================================");
                        System.out.println("         === Корзина клиента ===         ");
                        System.out.println("=====================================");
                        cartController.run(); // Переход к работе с корзиной.
                    }
                    case 0 -> {
                        System.out.println("Выход из программы.");
                        return; // Выход из программы.
                    }
                    default -> System.out.println("Неверный выбор. Попробуйте снова.");
                }
            }
        }
    }
}