package by.itstep.pronovich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.itstep.pronovich.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
