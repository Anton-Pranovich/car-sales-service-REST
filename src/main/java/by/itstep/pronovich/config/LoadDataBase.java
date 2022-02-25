package by.itstep.pronovich.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import by.itstep.pronovich.model.Car;
import by.itstep.pronovich.repository.CarRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(CarRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Car("BMW X5", 5.5)));
      log.info("Preloading " + repository.save(new Car("VW Golf", 7.6)));
    };
  }
}
