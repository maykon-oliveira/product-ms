package com.compassouol.productms.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author maykon-oliveira
 * @since 6/21/21 8:13 PM
 */
@Data
public class ApiError {
  private int statusCode;
  private String message;

  public ApiError(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  public static ApiError of(int statusCode, String message) {
    return new ApiError(statusCode, message);
  }

  @JsonProperty("status_code")
  public int getStatusCode() {
    return statusCode;
  }
}
