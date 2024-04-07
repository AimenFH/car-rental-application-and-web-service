package stud.fhcampuswien.ac.at.carRental.service.service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stud.fhcampuswien.ac.at.carRental.service.entity.Car;
import stud.fhcampuswien.ac.at.carRental.service.entity.Customer;
import stud.fhcampuswien.ac.at.carRental.service.exception.EntityNotFoundException;
import stud.fhcampuswien.ac.at.carRental.service.repository.CarRepository;
import stud.fhcampuswien.ac.at.carRental.service.repository.CustomerRepository;

@AllArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    
    @Override
    public Car getCar(Long id) {
        Optional<Car> car = carRepository.findById(id);
        return unwrapCar(car, id);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {  
        carRepository.deleteById(id);      
    }

    @Override
    public List<Car> getCars() {
        return (List<Car>)carRepository.findAll();
    }

    @Override
    public Car signCustomertoCar(Long customerId, Long carId) {
        Car car = getCar(carId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        Customer unwrapCustomer = CustomerServiceImpl.unwrapCustomer(customer, customerId);
        car.getCustomers().add(unwrapCustomer);
        return carRepository.save(car);
    }

    static Car unwrapCar(Optional<Car> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Car.class);
    }


}
