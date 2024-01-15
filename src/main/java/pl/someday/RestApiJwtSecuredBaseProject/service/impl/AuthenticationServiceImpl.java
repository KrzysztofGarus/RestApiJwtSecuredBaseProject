package pl.someday.RestApiJwtSecuredBaseProject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.someday.RestApiJwtSecuredBaseProject.dto.JWTAuthenticationResponse;
import pl.someday.RestApiJwtSecuredBaseProject.dto.SignUpRequest;
import pl.someday.RestApiJwtSecuredBaseProject.dto.SingInRequest;
import pl.someday.RestApiJwtSecuredBaseProject.model.Role;
import pl.someday.RestApiJwtSecuredBaseProject.model.User;
import pl.someday.RestApiJwtSecuredBaseProject.repository.UserRepository;
import pl.someday.RestApiJwtSecuredBaseProject.service.AuthenticationService;
import pl.someday.RestApiJwtSecuredBaseProject.service.JWTService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserServiceImpl customUserServiceImpl;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public void signUp(SignUpRequest signUpRequest) {
        User user = new User();

        user.setUsername(signUpRequest.getUsername());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
    }

    public JWTAuthenticationResponse signIn(SingInRequest singInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(singInRequest.getUsername(),
                singInRequest.getPassword()));
        var CustomUser = customUserServiceImpl.userDetailsService().loadUserByUsername(singInRequest.getUsername());
        var jwt = jwtService.generateToken(CustomUser);

        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);

        return jwtAuthenticationResponse;

    }

}
