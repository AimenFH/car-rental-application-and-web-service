package stud.fhcampuswien.ac.at.carRental.service.repository;


import org.springframework.data.repository.CrudRepository;
import stud.fhcampuswien.ac.at.carRental.service.entity.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
