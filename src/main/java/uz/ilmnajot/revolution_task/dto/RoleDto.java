package uz.ilmnajot.revolution_task.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.ilmnajot.revolution_task.enums.RoleType;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {


    private Long id;
    private String name;
    private RoleType roleType;
}
