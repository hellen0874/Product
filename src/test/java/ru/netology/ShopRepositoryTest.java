package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ShopRepositoryTest {
    @Test
    // Удаление существующего элемента
    public void ShouldRemoveById() {
        Product product1 = new Product(001, "Bread", 45);
        Product product2 = new Product(002, "Milk", 60);
        Product product3 = new Product(003, "Butter", 200);
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.remove(002);

        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
@Test
    // Удаление существующего элемента
    public void AttemptToRemoveByNonExistentId() {
        Product product1 = new Product(001, "Bread", 45);
        Product product2 = new Product(002, "Milk", 60);
        Product product3 = new Product(003, "Butter", 200);
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(004);
        });
    }


}
