package com.compassouol.productms.controller.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author maykon-oliveira
 * @since 6/21/21 7:48 PM
 */
@Data
@Validated
public class ProductCreateRequest {
  @NotBlank private String name;
  @NotBlank private String description;

  @NotNull private BigDecimal price;
}
