package stud.fhcampuswien.ac.at.carRental.service.web;

import java.util.List;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.AllArgsConstructor;
import stud.fhcampuswien.ac.at.carRental.service.entity.Invoice;
import stud.fhcampuswien.ac.at.carRental.service.service.InvoiceService;

@AllArgsConstructor
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    
    InvoiceService invoiceService;

    @GetMapping("/customer/{customerId}/Car/{carId}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long customerId, @PathVariable Long carId) {
        return new ResponseEntity<>(invoiceService.getInvoice(customerId, carId), HttpStatus.OK);
    }

    @PostMapping("/customer/{customerId}/Car/{carId}")
    public ResponseEntity<Invoice> saveInvoice(@Valid @RequestBody Invoice invoice, @PathVariable Long customerId, @PathVariable Long carId) {
        return new ResponseEntity<>(invoiceService.saveInvoice(invoice, customerId, carId), HttpStatus.CREATED);
    }


    @PutMapping("/customer/{customerId}/Car/{carId}")
    public ResponseEntity<Invoice> updateInvoice(@Valid @RequestBody Float price, @PathVariable Long customerId, @PathVariable Long carId) {
        return new ResponseEntity<>(invoiceService.updateInvoice(price, customerId, carId), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{customerId}/Car/{carId}")
    public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable Long customerId, @PathVariable Long carId) {
        invoiceService.deleteInvoice(customerId, carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Invoice>> getCustomerInvoices(@PathVariable Long customerId) {
        return new ResponseEntity<>(invoiceService.getCustomerInvoices(customerId), HttpStatus.OK);
    }

    @GetMapping("/Car/{carId}")
    public ResponseEntity<List<Invoice>> getCarInvoices(@PathVariable Long carId) {
        return new ResponseEntity<>(invoiceService.getCarInvoices(carId), HttpStatus.OK);
    }

}
