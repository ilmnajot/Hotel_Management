package uz.ilmnajot.revolution_task.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends RuntimeException {

    HttpStatus status;
    String message;

    @Override
    public String getMessage() {
        return message;
    }


    public AlreadyExistsException(String message) {
        this.message = message;
    }

    public AlreadyExistsException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
