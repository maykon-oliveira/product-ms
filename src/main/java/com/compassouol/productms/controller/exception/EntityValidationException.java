package com.compassouol.productms.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author maykon-oliveira
 * @since 6/9/21 4:26 PM
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntityValidationException extends RuntimeException {
  public EntityValidationException() {
    super("Validation Error");
  }
}
