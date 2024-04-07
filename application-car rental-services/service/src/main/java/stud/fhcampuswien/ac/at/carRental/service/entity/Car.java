package stud.fhcampuswien.ac.at.carRental.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Type cannot be blank")
    @NonNull
    @Column(name = "type", nullable = false)
    private String type;

    @NotBlank(message = "Model code cannot be blank")
    @NonNull
    @Column(name = "model", nullable = false)
    private String model;

    @NotBlank(message = "Price code cannot be blank")
    @NonNull
    @Column(name = "price", nullable = false)
    private String price;

    @NotBlank(message = "Description cannot be blank")
    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Set<Invoice> invoices;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "customer_car",
            inverseJoinColumns = @JoinColumn(name = "cutomer_id", referencedColumnName = "id")
    )
    private Set<Customer> customers;
}
