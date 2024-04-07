package stud.fhcampuswien.ac.at.carRental.service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stud.fhcampuswien.ac.at.carRental.service.entity.RefreshToken;
import stud.fhcampuswien.ac.at.carRental.service.entity.User;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserId(Long userId);


    @Modifying
    int deleteByUser(User user);
}
