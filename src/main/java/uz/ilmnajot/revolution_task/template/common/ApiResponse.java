package uz.ilmnajot.revolution_task.template.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import uz.ilmnajot.revolution_task.mapper.RoomMapper;

@Setter
@Getter
@AllArgsConstructor
public class ApiResponse {

    private boolean success;
    private String message;
    private Object data;
    private HttpStatus status;

    public ApiResponse(boolean success,String message, Object data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ApiResponse(String verified, HttpStatus httpStatus) {
            this.message = verified;
            this.status = httpStatus;

    }


}
