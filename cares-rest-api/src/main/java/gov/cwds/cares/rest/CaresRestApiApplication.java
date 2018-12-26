package gov.cwds.cares.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CWDS J Team
 */
@SpringBootApplication
@ComponentScan({"gov.ca.cwds.cares"})
public class CaresRestApiApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(CaresRestApiApplication.class, args);
  }
}