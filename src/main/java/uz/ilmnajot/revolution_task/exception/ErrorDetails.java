package uz.ilmnajot.revolution_task.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(String message, String details) {
        super();
        this.timestamp = new Date();
        this.message = message;
        this.details = details;

    }
}
