package uz.ilmnajot.revolution_task.exception;

public class NotFoundException extends RuntimeException {

    String message;

    @Override
    public String getMessage() {
        return message;
    }


    public NotFoundException(String message) {
        this.message = message;
    }


}
