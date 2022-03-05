package by.itstep.pronovich.payroll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import by.itstep.pronovich.controller.CarController;
import by.itstep.pronovich.model.Car;

@Component
public class CarModelAssembler implements RepresentationModelAssembler<Car, EntityModel<Car>> {

  @Override
  public EntityModel<Car> toModel(Car car) {

    return EntityModel.of(car, //
        linkTo(methodOn(CarController.class).oneCar(car.getId())).withSelfRel(),
        linkTo(methodOn(CarController.class).all()).withRel("cars"));
  }
}