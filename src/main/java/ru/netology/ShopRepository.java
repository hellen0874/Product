package ru.netology;

public class ShopRepository {
    private Product[] products = new Product[0];

    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     *
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    /**
     * Метод добавления товара в репозиторий
     *
     * @param newProduct — добавляемый товар
     */
    public void add(Product newProduct) {
        int addingId = newProduct.getId();
        for (Product product : products) {
            if (product.getId() == addingId) {
                throw new AlreadyExistsException(
                        "This id:" + addingId + "already exists, you can not add it."
                );
            }
        }
        products = addToArray(products, newProduct);
    }

    public Product[] findAll() {
        return products;
    }

    // Этот способ мы рассматривали в теории в теме про композицию
    public void remove(int id) {
        Product productFounded = findById(id);
        if (productFounded == null) {
            throw new NotFoundException(
                    "This id:" + id + "doesn't exist, you can not delete it"
            );
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
