package com.compassouol.productms.service;

import com.compassouol.productms.entity.Product;
import com.compassouol.productms.repository.ProductRepository;
import com.compassouol.productms.service.specification.CriteriaSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author maykon-oliveira
 * @since 6/21/21 7:43 PM
 */
@Service
@AllArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public Optional<Product> findById(String id) {
    return productRepository.findById(id);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public void deleteById(String id) {
    productRepository.deleteById(id);
  }

  public List<Product> findAll(CriteriaSpecification criteriaSpecification) {
    final var specification = criteriaSpecification.toSpecification();
    return productRepository.findAll(specification);
  }
}
