package stud.fhcampuswien.ac.at.carRental.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenValidationException extends AuthenticationException {

    public TokenValidationException(String message) {
        super(message);
    }
}
