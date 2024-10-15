package uz.ilmnajot.revolution_task.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uz.ilmnajot.revolution_task.entity.auth.Role;
@Data
public class UserRequest {

    private String fName;
    private String lName;
    private String username;
    private String pNumber;
    private String address;
    private String password;
    private String repeatPassword;
    private String verificationCode;
    private Long roleId;
}
