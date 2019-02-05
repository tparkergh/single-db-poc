package gov.ca.cwds.cares.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author CWDS J Team
 */
@SpringBootApplication
@ComponentScan(basePackages = {"gov.ca.cwds.cares", "gov.ca.cwds.cics", "gov.ca.cwds.bre"})
@EntityScan(basePackages = {"gov.ca.cwds.cares.persistence"})
@EnableJpaRepositories(basePackages = {"gov.ca.cwds.cares.persistence"})
public class CaresRestApiApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(CaresRestApiApplication.class, args);
  }
}