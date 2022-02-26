package by.itstep.pronovich.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.itstep.pronovich.exception.CarNotFoundException;
import by.itstep.pronovich.model.Car;
import by.itstep.pronovich.repository.CarRepository;


@RestController
public class CarController {

	private final CarRepository repository;

	@Autowired
	public CarController(CarRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/cars")
	List<Car> all() {
		return repository.findAll();
	}

	@GetMapping("/cars")
	List<Car> allByGroup() {
		return repository.findAll();
	}

	@PostMapping("/cars")
	Car newCar(@RequestBody Car newCar) {
		return repository.save(newCar);
	}

//	// Single item

	@GetMapping("/cars/{id}")
	Car one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
	}

//	@GetMapping("/cars/{id}")
//	EntityModel<Car> oneCar(@PathVariable Long id) {
//
//		Car car = repository.findById(id) //
//				.orElseThrow(() -> new CarNotFoundException(id));
//
//		return EntityModel.of(car, 
//				linkTo(methodOn(CarController.class).oneCar(id)).withSelfRel(),
//				linkTo(methodOn(CarController.class).all()).withRel("cars"));
//	}

	@PutMapping("/cars/{id}")
	Car replaceEmployee(@RequestBody Car newCar, @PathVariable Long id) {

		return repository.findById(id).map(car -> {
			car.setName(newCar.getName());
			car.setCost(newCar.getCost());
			return repository.save(car);
		}).orElseGet(() -> {
			newCar.setId(id);
			return repository.save(newCar);
		});
	}

	@DeleteMapping("/cars/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
