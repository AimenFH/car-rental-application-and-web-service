package stud.fhcampuswien.ac.at.carRental.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "InvoiceDateTime cannot be blank")
    @NonNull
    @Column(name = "invoice-date-time", nullable = false)
    private LocalDateTime invoiceDateTime;

    @NotBlank(message = "Price cannot be blank")
    @NonNull
    @Column(name = "price", nullable = false)
    private float price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

   @ManyToOne
   @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
    
}
