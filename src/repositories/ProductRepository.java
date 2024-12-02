package repositories;

import entities.Product;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Репозиторий для работы с файлами:
@Repository
public class ProductRepository {

    private final String filePath = "products.txt";

    // Метод для добавления продукта:
    public void save(Product product) {
        // Записываем в файл, начиная с заголовков, если файл пуст
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Записываем только если файл пустой.
            File file = new File(filePath);
            if (file.length() == 0) {
                writer.write("ID,Name,Price");
                writer.newLine();
            }

            writer.write(product.getId() + "," + product.getName() + "," + product.getPrice());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении продукта: " + e.getMessage());
        }
    }

    // Метод для получения всех продуктов:
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                // Пропускаем первую строку с заголовками:
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                BigDecimal price = new BigDecimal(parts[2]);

                products.add(new Product(id, name, price));
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + e.getMessage());
        }
        return products;
    }
}