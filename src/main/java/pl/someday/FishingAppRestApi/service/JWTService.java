package pl.someday.FishingAppRestApi.service;

import org.springframework.security.core.userdetails.UserDetails;
import pl.someday.FishingAppRestApi.model.User;

import java.util.HashMap;

public interface JWTService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

}
