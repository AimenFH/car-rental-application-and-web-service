package stud.fhcampuswien.ac.at.carRental.service.service;

import stud.fhcampuswien.ac.at.carRental.service.entity.Car;
import stud.fhcampuswien.ac.at.carRental.service.entity.Customer;

import java.util.List;


public interface CarService {
    Car getCar(Long id);
    Car saveCar(Car car);
    void deleteCar(Long id);    
    Car signCustomertoCar(Long coustmerId, Long carId);
    List<Car> getCars();

}