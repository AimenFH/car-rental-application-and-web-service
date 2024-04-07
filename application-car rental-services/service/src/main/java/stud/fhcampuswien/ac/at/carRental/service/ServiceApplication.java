package stud.fhcampuswien.ac.at.carRental.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import stud.fhcampuswien.ac.at.carRental.service.entity.User;
import stud.fhcampuswien.ac.at.carRental.service.repository.UserRepository;

@SpringBootApplication
@CrossOrigin
@EnableWebSecurity
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}