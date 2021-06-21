package com.compassouol.productms.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author maykon-oliveira
 * @since 6/21/21 6:45 PM
 */
@Data
@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String name;
  private String description;

  @Column(precision = 5, scale = 2)
  private BigDecimal price;
}
