package stud.fhcampuswien.ac.at.carRental.service.service;

import java.util.List;


import java.util.Optional;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import stud.fhcampuswien.ac.at.carRental.service.entity.Car;
import stud.fhcampuswien.ac.at.carRental.service.entity.Customer;
import stud.fhcampuswien.ac.at.carRental.service.entity.Invoice;
import stud.fhcampuswien.ac.at.carRental.service.exception.CustomerNotFoundException;
import stud.fhcampuswien.ac.at.carRental.service.exception.InvoiceNotFoundException;
import stud.fhcampuswien.ac.at.carRental.service.repository.CarRepository;
import stud.fhcampuswien.ac.at.carRental.service.repository.CustomerRepository;
import stud.fhcampuswien.ac.at.carRental.service.repository.InvoiceRepository;

@AllArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {
    

    private CustomerRepository customerRepository;
    private InvoiceRepository invoiceRepository;
    private CarRepository carRepository;
    
    @Override
    public Invoice getInvoice(Long customerId, Long carId) {
         Optional<Invoice> grade = invoiceRepository.findByCustomerIdAndCarId(customerId, carId);
         return unwrapInvoice(grade, customerId, carId);
    }

    @Override
    public Invoice saveInvoice(Invoice invoice, Long customerId, Long cardId) {
        Customer customer = CustomerServiceImpl.unwrapCustomer(customerRepository.findById(customerId), customerId);
        Car car = CarServiceImpl.unwrapCar(carRepository.findById(cardId), cardId);
        if (!customer.getCars().contains(car)) throw new CustomerNotFoundException(customerId, cardId);
        invoice.setCustomer(customer);
        invoice.setCar(car);
        return invoiceRepository.save(invoice);
    }



    @Override
    public Invoice updateInvoice(Float price, Long customerId, Long cardId) {
        Optional<Invoice> invoice = invoiceRepository.findByCustomerIdAndCarId(customerId, cardId);
        Invoice unwrappedGrade = unwrapInvoice(invoice, customerId, cardId);
        unwrappedGrade.setPrice(price);
        return invoiceRepository.save(unwrappedGrade);
    }

    @Override
    public void deleteInvoice(Long customerId, Long carId) {
        invoiceRepository.deleteByCustomerIdAndCarId(customerId, carId);
    }

    @Override
    public List<Invoice> getCustomerInvoices(Long customerId) {
        return invoiceRepository.findByCustomerId(customerId);
    }


    @Override
    public List<Invoice> getCarInvoices(Long carId) {
        return invoiceRepository.findByCarId(carId);
    }



    static Invoice unwrapInvoice(Optional<Invoice> entity, Long customerId, Long carId) {
        if (entity.isPresent()) return entity.get();
        else throw new InvoiceNotFoundException(customerId, carId);
    }

}
