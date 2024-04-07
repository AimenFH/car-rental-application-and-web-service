package stud.fhcampuswien.ac.at.carRental.service.service;

import stud.fhcampuswien.ac.at.carRental.service.entity.Invoice;

import java.util.List;



public interface InvoiceService {
    Invoice getInvoice(Long customerId, Long carID);
    Invoice saveInvoice(Invoice invoice, Long customerId, Long carID);
    Invoice updateInvoice(Float invoice, Long customerId, Long carID);
    void deleteInvoice(Long customerId, Long carID);
    List<Invoice> getCustomerInvoices(Long customerId);
    List<Invoice> getCarInvoices(Long carID);

}
