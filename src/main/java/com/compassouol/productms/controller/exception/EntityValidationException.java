package com.compassouol.productms.controller.exception;

/**
 * @author maykon-oliveira
 * @since 6/9/21 4:26 PM
 */
public class EntityValidationException extends RuntimeException {
  public EntityValidationException() {
    super("Validation Error");
  }
}
