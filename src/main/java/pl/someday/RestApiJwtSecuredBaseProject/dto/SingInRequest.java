package pl.someday.RestApiJwtSecuredBaseProject.dto;

import lombok.Data;

@Data
public class SingInRequest {

    private String email;
    private String password;
}
