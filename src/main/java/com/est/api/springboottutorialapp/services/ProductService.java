package com.est.api.springboottutorialapp.services;

import com.est.api.springboottutorialapp.entities.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product);
    Product update(Product product, int productId);
    void delete(int productId);
    Product getById(int productId);
    List<Product> getAll();
}
