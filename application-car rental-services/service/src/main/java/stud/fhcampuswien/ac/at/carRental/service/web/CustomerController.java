package stud.fhcampuswien.ac.at.carRental.service.web;

import java.util.Set;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.AllArgsConstructor;
import stud.fhcampuswien.ac.at.carRental.service.entity.Car;
import stud.fhcampuswien.ac.at.carRental.service.entity.Customer;
import stud.fhcampuswien.ac.at.carRental.service.service.CustomerService;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

//    @PostMapping("/register")
//    public ResponseEntity<Customer> createUser(@Valid @RequestBody Customer customer) {
//        customerService.savecustomer(customer);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//    @GetMapping("/login/{id}")
//    public ResponseEntity<String> findById(@PathVariable Long id) {
//        return new ResponseEntity<>(customerService.getcustomer(id).getName(), HttpStatus.OK);
//    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getcustomer(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getcustomer(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> savecustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.savecustomer(customer), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletecustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Customer>> getcustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}/cars")
    public ResponseEntity<Set<Car>> getRentalCars(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getRentalCars(id), HttpStatus.OK);
    }

}
