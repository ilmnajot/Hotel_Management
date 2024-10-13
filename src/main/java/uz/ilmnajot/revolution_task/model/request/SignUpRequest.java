package uz.ilmnajot.revolution_task.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequest {


    private String firstName;
    private String lastName;
    private String username;
    private String verificationCode;
    private String password;
    private String repeatPassword;
}
