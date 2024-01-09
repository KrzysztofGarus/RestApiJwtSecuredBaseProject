package pl.someday.RestApiJwtSecuredBaseProject.service;

import pl.someday.RestApiJwtSecuredBaseProject.dto.JWTAuthenticationResponse;
import pl.someday.RestApiJwtSecuredBaseProject.dto.SignUpRequest;
import pl.someday.RestApiJwtSecuredBaseProject.dto.SingInRequest;

public interface AuthenticationService {

    void signUp(SignUpRequest signUpRequest);

    JWTAuthenticationResponse signIn(SingInRequest singInRequest);
}
