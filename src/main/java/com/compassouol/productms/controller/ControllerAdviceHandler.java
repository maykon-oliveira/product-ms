package com.compassouol.productms.controller;

import com.compassouol.productms.controller.exception.EntityValidationException;
import com.compassouol.productms.internal.ApiError;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author maykon-oliveira
 * @since 6/9/21 4:34 PM
 */
@AllArgsConstructor
@ControllerAdvice
public class ControllerAdviceHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = {EntityValidationException.class})
  protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
    final var headers = new HttpHeaders();
    final var badRequest = HttpStatus.BAD_REQUEST;
    if (ex instanceof EntityValidationException) {
      final var apiError = ApiError.of(badRequest.value(), ex.getMessage());
      return handleExceptionInternal(ex, apiError, headers, badRequest, request);
    }
    return handleExceptionInternal(ex, null, headers, badRequest, request);
  }
}
