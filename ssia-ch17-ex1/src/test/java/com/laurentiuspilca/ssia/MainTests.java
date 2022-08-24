package com.laurentiuspilca.ssia;

import com.laurentiuspilca.ssia.model.Product;
import com.laurentiuspilca.ssia.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MainTests {

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("When the method is called without a user, " +
            "it throws AuthenticationException")
    void testProductServiceWithNoUser() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("beer", "nikolai"));
        products.add(new Product("candy", "nikolai"));
        products.add(new Product("chocolate", "julien"));

        assertThrows(AuthenticationException.class,
                () -> productService.sellProducts(products));
    }

    @Test
    @DisplayName("When the method is called with an authenticated user, " +
            "it only returns products for the authenticated user")
    @WithMockUser(username = "julien")
    void testProductServiceWithUser() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("beer", "nikolai"));
        products.add(new Product("candy", "nikolai"));
        products.add(new Product("chocolate", "julien"));

        var result = productService.sellProducts(products);
        var expected = List.of(new Product("chocolate", "julien"));

        assertEquals(expected, result);
    }

}
