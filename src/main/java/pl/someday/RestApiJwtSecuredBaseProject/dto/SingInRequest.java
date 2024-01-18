package pl.someday.RestApiJwtSecuredBaseProject.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SingInRequest {

    @Email
    private String username;
    @NotBlank
    private String password;
}
