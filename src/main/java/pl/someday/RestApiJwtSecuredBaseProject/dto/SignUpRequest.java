package pl.someday.RestApiJwtSecuredBaseProject.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String password;

}
