package gov.ca.cwds.bre.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CWDS J Team
 */
@SpringBootApplication
@ComponentScan("gov.ca.cwds.bre")
public class CaresBreRestApiApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(CaresBreRestApiApplication.class, args);
  }
}
