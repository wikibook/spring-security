package com.laurentiuspilca.ssia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import com.laurentiuspilca.ssia.repositories.ProductRepository;

@SpringBootTest
@EnableAutoConfiguration(
        exclude = {DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class})
class MainTests {

    @MockBean
    private ProductRepository productRepository;

    @Test
    @DisplayName("When the method is called without a user, " +
            "it returns nothing")
    void testProductServiceWithNoUser() {
        var result = productRepository.findProductByNameContains("c");

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("When the method is called with an authenticated user, " +
            "it only returns products for the authenticated user")
    @WithMockUser(username = "julien")
    void testProductServiceWithUser() {
        var result = productRepository.findProductByNameContains("c");

        result.forEach(p -> {
            assertEquals("julien", p.getOwner());
            assertTrue(p.getName().contains("c"));
        });
    }
}
