package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ShopRepositoryTest {
    @Test
    // К заданию 1: удаление существующего элемента
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
    // К заданию 1: удаление продукта с несуществующим ID
    public void AttemptToRemoveNonExistentId() {
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

    @Test
    // К заданию 2: добавление нового продукта в уже заполненный репозиторий
    public void ShouldAddNewProduct() {
        Product product1 = new Product(001, "Bread", 45);
        Product product2 = new Product(002, "Milk", 60);
        Product product3 = new Product(003, "Butter", 200);
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        Product product4 = new Product(004, "Cheese", 700);
        repo.add(product4);

        Product[] expected = {product1, product2, product3, product4};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    // К заданию 2: добавление продукта с уже существующим ID
    // Зачем этот тест, если добавление продукта уже проверяется первым тестом по заданию 1 ?
    public void AttemptToAddPreExistingId() {
        Product product1 = new Product(001, "Bread", 45);
        Product product2 = new Product(002, "Milk", 60);
        Product product3 = new Product(003, "Butter", 200);
        ShopRepository repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            Product product4 = new Product(003, "Cheese", 700);
            repo.add(product4);
        });
    }
}
