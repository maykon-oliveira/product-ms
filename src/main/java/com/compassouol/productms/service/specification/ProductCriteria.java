package com.compassouol.productms.service.specification;

import com.compassouol.productms.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

/**
 * @author maykon-oliveira
 * @since 6/9/21 2:42 PM
 */
@Getter
@Setter
public class ProductCriteria implements CriteriaSpecification<Product> {
  private String q;
  private String minPrice;
  private String maxPrice;

  @Override
  public Specification<Product> toSpecification() {
    Specification<Product> specification = Specification.where(null);

    if (Objects.nonNull(q) && !q.isBlank()) {
      specification =
          specification.and(
              (root, cq, cb) ->
                  cb.or(
                      cb.like(root.get("name"), q + "%"),
                      cb.like(root.get("description"), q + "%")));
    }
    if (Objects.nonNull(minPrice) && !minPrice.isBlank()) {
      specification =
          specification.and((root, cq, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice));
    }
    if (Objects.nonNull(maxPrice) && !maxPrice.isBlank()) {
      specification =
          specification.and((root, cq, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice));
    }
    return specification;
  }
}
