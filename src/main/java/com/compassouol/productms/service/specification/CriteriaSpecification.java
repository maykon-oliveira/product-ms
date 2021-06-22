package com.compassouol.productms.service.specification;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author maykon-oliveira
 * @since 6/9/21 2:44 PM
 */
public interface CriteriaSpecification<T> {
  Specification<T> toSpecification();
}
