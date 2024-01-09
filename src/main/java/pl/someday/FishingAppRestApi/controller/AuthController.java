package pl.someday.FishingAppRestApi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.someday.FishingAppRestApi.dto.JWTAuthenticationResponse;
import pl.someday.FishingAppRestApi.dto.SignUpRequest;
import pl.someday.FishingAppRestApi.dto.SingInRequest;
import pl.someday.FishingAppRestApi.service.AuthenticationService;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest){
        authenticationService.signUp(signUpRequest);
        return ResponseEntity.ok("User account created");
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthenticationResponse> signIn(@RequestBody SingInRequest singInRequest){
        return ResponseEntity.ok(authenticationService.signIn(singInRequest));
    }
}
