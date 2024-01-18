package pl.someday.RestApiJwtSecuredBaseProject.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserService {

    UserDetailsService userDetailsService();

    boolean isUsernameAlreadySigned(String username);

    boolean doesUserExist(String username);

}
