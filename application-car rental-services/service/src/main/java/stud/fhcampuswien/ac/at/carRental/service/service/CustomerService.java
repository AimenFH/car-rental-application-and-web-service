package stud.fhcampuswien.ac.at.carRental.service.service;

import java.util.Set;


import stud.fhcampuswien.ac.at.carRental.service.entity.Car;
import stud.fhcampuswien.ac.at.carRental.service.entity.Customer;

public interface CustomerService {
    Customer getcustomer(Long id);
    Customer savecustomer(Customer customer);
    void deleteCustomer(Long id);
    Set<Customer> getCustomers();
    Set<Car> getRentalCars(Long id);
}