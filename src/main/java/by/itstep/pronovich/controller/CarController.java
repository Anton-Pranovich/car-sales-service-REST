package by.itstep.pronovich.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.itstep.pronovich.exception.CarNotFoundException;
import by.itstep.pronovich.model.Car;
import by.itstep.pronovich.payroll.CarModelAssembler;
import by.itstep.pronovich.repository.CarRepository;

@RestController
public class CarController {

	private final CarRepository repository;
	private final CarModelAssembler assembler;

	CarController(CarRepository repository, CarModelAssembler assembler) {

		this.repository = repository;
		this.assembler = assembler;
	}

	@Autowired
	public CarController(CarRepository repository) {
		this.repository = repository;
		this.assembler = new CarModelAssembler();
	}

//	@GetMapping("/cars")
//	List<Car> all() {
//		return repository.findAll();
//	}

	@GetMapping("/cars")
	public CollectionModel<EntityModel<Car>> all() {

		List<EntityModel<Car>> employees = repository.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(employees, linkTo(methodOn(CarController.class).all()).withSelfRel());
	}

//	@PostMapping("/cars")
//	public Car newCar(@RequestBody @Valid Car newCar, BindingResult bindingResult) {
//		// String answer;
//		if (bindingResult.hasErrors()) {
////			log.info("Returning addProduct.jsp page");
////			answer = "Invalid input data";
//			throw new CarNotFoundException("invalid input data");
//		}
//		return repository.save(newCar);
//	}
	@PostMapping("/cars")
	public ResponseEntity <Car> newCar(@RequestBody @Valid Car newCar, BindingResult bindingResult) {
		Car car = repository.save(newCar);
		EntityModel<Car> model =  EntityModel.of(car, 
				linkTo(methodOn(CarController.class).oneCar(car.getId())).withSelfRel(),
				linkTo(methodOn(CarController.class).all()).withRel("cars"));
		
		
		return ResponseEntity.created(
				model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(car);
			
		
	}

//	// Single item

//	@GetMapping("/cars/{id}")
//	Car one(@PathVariable Long id) {
//		return repository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
//	}

	@GetMapping("/cars/{id}")
	public EntityModel<Car> oneCar(@PathVariable Long id) {

		Car car = repository.findById(id) //
				.orElseThrow(() -> new CarNotFoundException(id));

		return EntityModel.of(car, 
				linkTo(methodOn(CarController.class).oneCar(id)).withSelfRel(),
				linkTo(methodOn(CarController.class).all()).withRel("cars"));
	}

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
	ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
