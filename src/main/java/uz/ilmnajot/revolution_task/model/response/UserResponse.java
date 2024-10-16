package uz.ilmnajot.revolution_task.model.response;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String fName;
    private String lName;
    private String username;
    private String pNumber;
    private String address;
    private Long roleId;
}
