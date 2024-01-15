package pl.someday.RestApiJwtSecuredBaseProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class JWTAuthenticationResponse {

    private String token;
}
