package stud.fhcampuswien.ac.at.carRental.service.repository;

import org.springframework.data.repository.CrudRepository;


import stud.fhcampuswien.ac.at.carRental.service.entity.Car;


public interface CarRepository extends CrudRepository<Car, Long> {

}