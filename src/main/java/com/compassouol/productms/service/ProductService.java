package com.compassouol.productms.service;

import com.compassouol.productms.entity.Product;
import com.compassouol.productms.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author maykon-oliveira
 * @since 6/21/21 7:43 PM
 */
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product save (Product product) {
        return productRepository.save(product);
    }
}
