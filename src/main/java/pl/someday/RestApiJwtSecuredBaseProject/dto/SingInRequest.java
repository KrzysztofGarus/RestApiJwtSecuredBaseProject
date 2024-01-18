package pl.someday.RestApiJwtSecuredBaseProject.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SingInRequest {

    @Valid

    @Email(message = "Email should be valid")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
