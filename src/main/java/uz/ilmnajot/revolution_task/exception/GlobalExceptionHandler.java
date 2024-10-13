package uz.ilmnajot.revolution_task.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public HttpEntity<?> handleNotFoundException(NotFoundException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    public HttpEntity<?> handler(Exception exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(Exception.class)
    public HttpEntity<?> handleException(Exception exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
