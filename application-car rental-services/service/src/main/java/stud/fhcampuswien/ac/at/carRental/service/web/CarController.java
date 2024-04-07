package stud.fhcampuswien.ac.at.carRental.service.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


import lombok.AllArgsConstructor;
import stud.fhcampuswien.ac.at.carRental.service.entity.Car;
import stud.fhcampuswien.ac.at.carRental.service.entity.Customer;
import stud.fhcampuswien.ac.at.carRental.service.service.CarService;

@AllArgsConstructor
@RestController
@RequestMapping("/car")
public class CarController {

    CarService carService;

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        return new ResponseEntity<>(carService.getCar(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> saveCar(@Valid @RequestBody Car car) {
        return new ResponseEntity<>(carService.saveCar(car), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
    }

    @PutMapping("/{carId}/customer/{customerId}")
    public ResponseEntity<Car> signCustomertoCar(@PathVariable Long carId, @PathVariable Long customerId) {
        return new ResponseEntity<>(carService.signCustomertoCar(customerId, carId), HttpStatus.OK);
    }

}