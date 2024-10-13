package uz.ilmnajot.revolution_task.exception;

public class AlreadyExistsException extends RuntimeException {

    String message;

    @Override
    public String getMessage() {
        return message;
    }


    public AlreadyExistsException(String message) {
        this.message = message;
    }


}
