package uz.ilmnajot.revolution_task.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequest {

    private String fName;
    private String lName;
    private String username;
    private String pNumber;
    private String address;
    private String password;
    private String repeatPassword;

}
