package pl.someday.FishingAppRestApi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.someday.FishingAppRestApi.dto.JWTAuthenticationResponse;
import pl.someday.FishingAppRestApi.dto.SignUpRequest;
import pl.someday.FishingAppRestApi.dto.SingInRequest;
import pl.someday.FishingAppRestApi.model.Role;
import pl.someday.FishingAppRestApi.model.User;
import pl.someday.FishingAppRestApi.repository.UserRepository;
import pl.someday.FishingAppRestApi.service.AuthenticationService;
import pl.someday.FishingAppRestApi.service.JWTService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public void signUp(SignUpRequest signUpRequest) {
        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
    }

    public JWTAuthenticationResponse signIn(SingInRequest singInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(singInRequest.getEmail(),
                singInRequest.getPassword()));
        var user = userRepository.findByEmail(singInRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);

        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);

        return jwtAuthenticationResponse;

    }

}
