package com.compassouol.productms.controller;

import com.compassouol.productms.controller.dto.ProductCreateRequest;
import com.compassouol.productms.entity.Product;
import com.compassouol.productms.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
  @Mock private ProductService productService;
  @Mock ModelMapper mapper;
  private Product product;
  @InjectMocks private ProductController productController;
  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    product =
        Product.builder()
            .id("AAA")
            .name("Book")
            .description("sdsd")
            .price(new BigDecimal("15.99"))
            .build();
    mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
  }

  @AfterEach
  void tearDown() {
    product = null;
  }

  @Test
  @DisplayName("Given a invalid product,  it should return bad request")
  void saveInvalidProduct() throws Exception {
    final var createRequest = new ProductCreateRequest();
    createRequest.setDescription("");
    mockMvc
        .perform(
            post("/products").contentType(APPLICATION_JSON).content(asJsonString(createRequest)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Given a valid product, it should return 201 status code")
  void saveValidProduct() throws Exception {
    final var createRequest = new ProductCreateRequest();
    createRequest.setDescription("Description");
    createRequest.setName("Book");
    createRequest.setPrice(new BigDecimal("10"));

    when(mapper.map(createRequest, Product.class)).thenReturn(product);

    mockMvc
        .perform(
            post("/products").contentType(APPLICATION_JSON).content(asJsonString(createRequest)))
        .andDo(print())
        .andExpect(status().isCreated());

    verify(productService, times(1)).save(product);
  }

  @Test
  void findAll() throws Exception {
    mockMvc.perform(get("/products")).andExpect(status().isOk());

    verify(productService, times(1)).findAll();
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
