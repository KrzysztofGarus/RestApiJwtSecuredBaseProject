package pl.someday.RestApiJwtSecuredBaseProject.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UsernameAlreadyExistsException extends DataIntegrityViolationException {

    public UsernameAlreadyExistsException() {
        super("Username already exists");
    }
}