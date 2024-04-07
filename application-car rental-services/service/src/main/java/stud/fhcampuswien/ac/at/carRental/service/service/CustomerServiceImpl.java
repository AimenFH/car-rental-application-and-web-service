package stud.fhcampuswien.ac.at.carRental.service.service;



import org.springframework.stereotype.Service;



import lombok.AllArgsConstructor;
import stud.fhcampuswien.ac.at.carRental.service.entity.Car;
import stud.fhcampuswien.ac.at.carRental.service.entity.Customer;
import stud.fhcampuswien.ac.at.carRental.service.exception.EntityNotFoundException;
import stud.fhcampuswien.ac.at.carRental.service.repository.CustomerRepository;


import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {


    private CustomerRepository customerRepository;

    @Override
    public Customer getcustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return unwrapCustomer(customer, id);
    }

    @Override
    public Customer savecustomer(Customer student) {
        return customerRepository.save(student);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Set<Customer> getCustomers() {
        return (Set<Customer>) customerRepository.findAll();
    }

    @Override
    public Set<Car> getRentalCars(Long id) {
        Customer customer = getcustomer(id);
        return customer.getCars();
    }


    static Customer unwrapCustomer(Optional<Customer> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Customer.class);
    }

}