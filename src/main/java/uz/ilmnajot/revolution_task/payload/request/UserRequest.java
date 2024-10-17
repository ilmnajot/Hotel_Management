package uz.ilmnajot.revolution_task.payload.request;

import lombok.Data;

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
