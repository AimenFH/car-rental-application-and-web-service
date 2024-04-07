package stud.fhcampuswien.ac.at.carRental.service.repository;

import org.springframework.data.repository.CrudRepository;


import stud.fhcampuswien.ac.at.carRental.service.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}