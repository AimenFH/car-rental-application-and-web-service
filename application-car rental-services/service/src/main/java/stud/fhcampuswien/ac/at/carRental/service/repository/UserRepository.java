package stud.fhcampuswien.ac.at.carRental.service.repository;


import org.springframework.data.repository.CrudRepository;
import stud.fhcampuswien.ac.at.carRental.service.entity.User;


import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}