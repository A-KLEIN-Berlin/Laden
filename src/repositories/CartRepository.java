package repositories;

import entities.Cart;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Репозиторий для работы с корзинами:
@Repository
public class CartRepository {

    private final String filePath = "cart.txt";
    private Map<Integer, Cart> cartMap = new HashMap<>();

    // Метод для сохранения корзины в файл:
    public void save(Cart cart) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(cart);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении корзины: " + e.getMessage());
        }
    }

    // Метод для загрузки корзины из файла:
    public Cart load(int cartId) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Cart) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка при загрузке корзины: " + e.getMessage());
        }
    }
}
