package stud.fhcampuswien.ac.at.carRental.service.service;

import java.util.Optional;

import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import lombok.AllArgsConstructor;
import stud.fhcampuswien.ac.at.carRental.service.entity.Role;
import stud.fhcampuswien.ac.at.carRental.service.entity.User;
import stud.fhcampuswien.ac.at.carRental.service.exception.AssignedRoleException;
import stud.fhcampuswien.ac.at.carRental.service.exception.EntityNotFoundException;
import stud.fhcampuswien.ac.at.carRental.service.repository.RoleRepository;
import stud.fhcampuswien.ac.at.carRental.service.repository.UserRepository;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public User getUser(String username) {

        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user, 404L);
    }

    @Override
    public User saveUser(User userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return null;
    }

    static User unwrapUser(Optional<User> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }


    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public User addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("This User Does Not Exists!"));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException("This Role Does Not Exists!"));
        if(user.getRoles().contains(role)) {
            throw new AssignedRoleException("User Already Has This Role");
        }
        user.getRoles().add(role);
        role.getUsers().add(user);
        return userRepository.save(user);
    }

    
}
