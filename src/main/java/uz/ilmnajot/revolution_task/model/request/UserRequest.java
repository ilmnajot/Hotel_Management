package uz.ilmnajot.revolution_task.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uz.ilmnajot.revolution_task.entity.auth.Role;
@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String username;
    private Long roleId;
}
