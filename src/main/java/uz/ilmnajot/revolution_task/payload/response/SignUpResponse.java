package uz.ilmnajot.revolution_task.payload.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.revolution_task.entity.auth.User;

@Setter
@Getter
public class SignUpResponse {

    private Long id;
    private String fName;
    private String lName;
    private String username;
    private String pNumber;
    private String address;
    private String password;
    private String repeatPassword;


    public static SignUpResponse toSignUpResponse(User user) {
        SignUpResponse response = new SignUpResponse();
        response.setId(user.getId());
        response.setFName(user.getFName());
        response.setLName(user.getLName());
        response.setUsername(user.getUsername());
        response.setPNumber(user.getPNumber());
        response.setAddress(user.getAddress());
        return response;
    }

}
