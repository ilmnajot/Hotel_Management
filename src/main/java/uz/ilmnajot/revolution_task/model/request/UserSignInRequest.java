package uz.ilmnajot.revolution_task.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignInRequest {

    private String username;
    private String password;
}
