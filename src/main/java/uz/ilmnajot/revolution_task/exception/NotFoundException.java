package uz.ilmnajot.revolution_task.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

    String message;
    HttpStatus status;

    @Override
    public String getMessage() {
        return message;
    }

    public NotFoundException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public NotFoundException(String message) {
        this.message = message;
    }


}
