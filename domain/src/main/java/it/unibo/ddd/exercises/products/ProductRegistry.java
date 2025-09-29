package it.unibo.ddd.exercises.products;

import it.unibo.ddd.exercises.money.Money;

import java.util.function.Predicate;

public interface ProductRegistry {
    Iterable<Product> getAllProducts();

    Product findProductByID(ProductID id);

    Iterable<Product> findProductByPrice(Predicate<Money> predicate);

    void addNewProduct(Product product);

    void updateProduct(Product product);

    void removeProduct(Product product);
}
