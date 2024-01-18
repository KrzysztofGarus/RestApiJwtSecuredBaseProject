package pl.someday.RestApiJwtSecuredBaseProject.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.someday.RestApiJwtSecuredBaseProject.dto.JWTAuthenticationResponse;
import pl.someday.RestApiJwtSecuredBaseProject.dto.SignUpRequest;
import pl.someday.RestApiJwtSecuredBaseProject.dto.SingInRequest;
import pl.someday.RestApiJwtSecuredBaseProject.exception.CustomUsernameNotFoundException;
import pl.someday.RestApiJwtSecuredBaseProject.exception.UsernameAlreadyExistsException;
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

    public void signUp(SignUpRequest signUpRequest) throws UsernameAlreadyExistsException {
        String username = signUpRequest.getUsername();
        if (customUserServiceImpl.isUsernameAlreadySigned(username)) throw new UsernameAlreadyExistsException();
        User user = new User();
        user.setUsername(username);
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);
    }

    public JWTAuthenticationResponse signIn(SingInRequest singInRequest) throws CustomUsernameNotFoundException {
        String username = singInRequest.getUsername();
        if (!customUserServiceImpl.doesUserExist(username)) throw new CustomUsernameNotFoundException(username);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                singInRequest.getPassword()));
        var CustomUser = customUserServiceImpl.userDetailsService().loadUserByUsername(username);
        var jwt = jwtService.generateToken(CustomUser);

        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);

        return jwtAuthenticationResponse;
    }

    
}
