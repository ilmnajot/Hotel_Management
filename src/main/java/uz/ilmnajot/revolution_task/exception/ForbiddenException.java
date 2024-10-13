package uz.ilmnajot.revolution_task.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ForbiddenException extends RuntimeException {

    String message;

    public ForbiddenException(String message, HttpStatus forbidden) {
        this.message = message + " " + forbidden;
    }

}
