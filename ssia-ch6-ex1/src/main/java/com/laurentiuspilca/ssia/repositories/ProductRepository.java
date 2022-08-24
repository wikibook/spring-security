package com.laurentiuspilca.ssia.repositories;

import com.laurentiuspilca.ssia.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
