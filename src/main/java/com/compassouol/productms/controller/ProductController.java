package com.compassouol.productms.controller;

import com.compassouol.productms.controller.dto.ProductCreateRequest;
import com.compassouol.productms.controller.exception.EntityValidationException;
import com.compassouol.productms.entity.Product;
import com.compassouol.productms.service.ProductService;
import com.compassouol.productms.util.ControllerConstants;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @author maykon-oliveira
 * @since 6/21/21 7:38 PM
 */
@RestController
@AllArgsConstructor
@RequestMapping(ControllerConstants.PRODUCT_PATH)
public class ProductController {
  private final ProductService productService;
  private final ModelMapper mapper;

  @PostMapping
  @ResponseStatus(CREATED)
  public Product save(
      @Valid @RequestBody ProductCreateRequest createRequest, BindingResult result) {
    if (result.hasErrors()) {
      throw new EntityValidationException();
    }
    final var product = mapper.map(createRequest, Product.class);
    return productService.save(product);
  }

  @GetMapping("{id}")
  public ResponseEntity<Product> findBYId(@PathVariable String id) {
    final var optionalProduct = productService.findById(id);

    return optionalProduct
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
