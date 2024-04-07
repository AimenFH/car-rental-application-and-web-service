package stud.fhcampuswien.ac.at.carRental.service.exception;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(Long customerId, Long carId) {
        super("The invoice with customer id: '" + customerId + "' and Car id: '" + carId + "' does not exist in our records");
    }
    
}