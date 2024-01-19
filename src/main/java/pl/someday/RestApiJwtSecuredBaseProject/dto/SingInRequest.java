package pl.someday.RestApiJwtSecuredBaseProject.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record SingInRequest(
    
    @Valid 
    
    @Email(message = "Email should be valid") 
    String username,

    @NotBlank(message = "Password cannot be blank") 
    String password) {
}
