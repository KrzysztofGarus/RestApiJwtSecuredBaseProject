package pl.someday.RestApiJwtSecuredBaseProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.someday.RestApiJwtSecuredBaseProject.dto.SignUpRequest;
import pl.someday.RestApiJwtSecuredBaseProject.service.AuthenticationService;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void signUpTest() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setFirstName("user");
        signUpRequest.setLastName("user");
        signUpRequest.setUsername("user");
        signUpRequest.setPassword("user");

        doNothing().when(authenticationService).signUp(signUpRequest);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("User account created"));
    }
}
