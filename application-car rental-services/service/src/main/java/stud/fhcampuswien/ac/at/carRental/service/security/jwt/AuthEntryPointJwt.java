package stud.fhcampuswien.ac.at.carRental.service.security.jwt;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import stud.fhcampuswien.ac.at.carRental.service.exception.ErrorMessage;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {


  private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPointJwt.class);

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    ErrorMessage body = new ErrorMessage(
            HttpStatus.UNAUTHORIZED.value(),
            new Date(),
            "General Authentication Error (Invalid JWT, Unknown JWT Format), Please get new JWT Token!",
            String.format("uri=%s", request.getRequestURI())
    );


    final ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    mapper.setDateFormat(dateFormat);

    mapper.writeValue(response.getOutputStream(), body);
  }

}
