package com.inditex.ecommerce.rest_web.config;

import com.inditex.ecommerce.rest_web.controlleradvice.RestResponseEntityExceptionHandler;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@ContextConfiguration(classes = {PricesWebConfig.class, RestResponseEntityExceptionHandler.class})
@EnableWebMvc
public @interface TestConfig {

}
