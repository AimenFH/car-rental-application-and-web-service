package stud.fhcampuswien.ac.at.carRental.service.security.jwt;

import java.security.Key;
import java.util.Date;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import stud.fhcampuswien.ac.at.carRental.service.exception.TokenValidationException;
import stud.fhcampuswien.ac.at.carRental.service.security.services.UserDetailsImpl;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${spring.app.jwtSecret}")
  private String jwtSecret;

  @Value("${spring.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(Authentication authentication) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    return generateTokenFromUsername(userPrincipal.getUsername());

  }

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String generateTokenFromUsername(String username) {
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(key()).build()
            .parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) throws TokenValidationException {
    try {
      Jwts.parser().setSigningKey(key()).build().parse(authToken);
      return true;
    } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException |
             SignatureException e) {
      LOGGER.error("Invalid JWT token: {}", e.getMessage());
      throw new TokenValidationException(e.getMessage());
    }

  }
}
