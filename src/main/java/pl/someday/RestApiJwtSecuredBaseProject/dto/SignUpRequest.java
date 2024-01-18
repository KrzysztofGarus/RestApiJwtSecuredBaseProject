package pl.someday.RestApiJwtSecuredBaseProject.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {

    @Valid

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email(message = "Invalid email address")
    private String username;
    @NotBlank
    @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters long")
    private String password;

}
