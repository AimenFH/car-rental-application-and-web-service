package stud.fhcampuswien.ac.at.carRental.service.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long customerId, Long carId) {
        super("The customer with id: '" + customerId + "' is not Found for The Car with id: '" + carId);
    }
    
}