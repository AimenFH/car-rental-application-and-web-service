package stud.fhcampuswien.ac.at.carRental.service.service;


import stud.fhcampuswien.ac.at.carRental.service.entity.User;

import java.util.Optional;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User userEntity);

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    User addRoleToUser(String username, String roleName);
}