package stud.fhcampuswien.ac.at.carRental.service.security.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stud.fhcampuswien.ac.at.carRental.service.entity.User;
import stud.fhcampuswien.ac.at.carRental.service.repository.UserRepository;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

}
