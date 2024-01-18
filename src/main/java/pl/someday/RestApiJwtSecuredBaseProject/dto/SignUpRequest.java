package pl.someday.RestApiJwtSecuredBaseProject.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {

    @Valid

    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    @Email(message = "Email should be valid")
    private String username;
    @NotBlank
    @Size(min = 8, max = 30, message = "Password must be between 6 and 30 characters long")
    private String password;

}
