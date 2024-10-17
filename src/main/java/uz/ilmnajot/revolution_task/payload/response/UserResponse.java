package uz.ilmnajot.revolution_task.payload.response;

import lombok.Data;
import org.springframework.stereotype.Component;
import uz.ilmnajot.revolution_task.entity.auth.User;

@Data
@Component
public class UserResponse {

    private Long id;
    private String fName;
    private String lName;
    private String username;
    private String pNumber;
    private String address;
    private Long roleId;


    public UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFName(user.getFName());
        response.setLName(user.getLName());
        response.setUsername(user.getUsername());
        response.setPNumber(user.getPNumber());
        response.setAddress(user.getAddress());
        return response;
    }


}
