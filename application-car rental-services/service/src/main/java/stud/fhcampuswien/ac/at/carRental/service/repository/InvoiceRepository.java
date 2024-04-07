package stud.fhcampuswien.ac.at.carRental.service.repository;

import java.util.List;
import java.util.Optional;


import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;


import stud.fhcampuswien.ac.at.carRental.service.entity.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
     Optional<Invoice> findByCustomerIdAndCarId(Long customerId, Long carId);
     List<Invoice> findByCustomerId(Long customerId);
     List<Invoice> findByCarId(Long carId);
     @Transactional
     void deleteByCustomerIdAndCarId(Long customerId, Long carId);

}