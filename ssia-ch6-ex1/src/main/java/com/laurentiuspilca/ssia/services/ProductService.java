package com.laurentiuspilca.ssia.services;

import com.laurentiuspilca.ssia.entities.Product;
import com.laurentiuspilca.ssia.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
