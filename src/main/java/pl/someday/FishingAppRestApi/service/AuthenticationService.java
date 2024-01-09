package pl.someday.FishingAppRestApi.service;

import pl.someday.FishingAppRestApi.dto.JWTAuthenticationResponse;
import pl.someday.FishingAppRestApi.dto.SignUpRequest;
import pl.someday.FishingAppRestApi.dto.SingInRequest;

public interface AuthenticationService {

    void signUp(SignUpRequest signUpRequest);

    JWTAuthenticationResponse signIn(SingInRequest singInRequest);
}
