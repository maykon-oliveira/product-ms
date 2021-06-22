package com.compassouol.productms.service;

import com.compassouol.productms.entity.Product;
import com.compassouol.productms.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
  @Mock private ProductRepository productRepository;
  @InjectMocks private ProductService productService;

  private Product product1;
  private Product product2;
  List<Product> productList;

  @BeforeEach
  public void setUp() {
    productList = new ArrayList<>();
    product1 =
        Product.builder()
            .id("AAA")
            .name("Book AAA")
            .description("A description")
            .price(new BigDecimal("79.99"))
            .build();
    product2 =
        Product.builder()
            .id("ABA")
            .name("Book ABA")
            .description("A description")
            .price(new BigDecimal("70.99"))
            .build();
    productList.add(product1);
    productList.add(product2);
  }

  @AfterEach
  public void tearDown() {
    product1 = product2 = null;
    productList = null;
  }

  @Test
  @DisplayName("Given a product, it should call repository save method")
  void save() {
    when(productRepository.save(any())).thenReturn(product1);
    productService.save(product1);
    verify(productRepository, times(1)).save(any());
  }

  @Test
  void findAll() {
    when(productRepository.findAll()).thenReturn(productList);
    var productList1 = productService.findAll();
    assertEquals(productList1, productList);
    verify(productRepository, times(1)).findAll();
  }

  @Test
  void findById() {
    var productId = "ABA";
    when(productRepository.findById(productId)).thenReturn(Optional.of(product2));
    final var optionalProduct = productService.findById(productId);
    assertTrue(optionalProduct.isPresent());
    assertEquals(product2, optionalProduct.get());
  }

  @Test
  void deleteById() {
    productService.deleteById(anyString());
    verify(productRepository, times(1)).deleteById(anyString());
  }
}
