package com.compassouol.productms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author maykon-oliveira
 * @since 6/9/21 3:59 PM
 */
@Configuration
public class BeansConfiguration {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
