package uz.ilmnajot.revolution_task.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import uz.ilmnajot.revolution_task.entity.auth.Role;
import uz.ilmnajot.revolution_task.enums.Authority;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String fName;
    private String lName;
    private String username;
    private String pNumber;
    private String address;
    private String verificationCode;
    private String password;
    private Long roleId;
    private boolean isEnabled;
}
