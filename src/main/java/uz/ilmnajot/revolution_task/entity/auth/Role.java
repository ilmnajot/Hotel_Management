package uz.ilmnajot.revolution_task.entity.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.ilmnajot.revolution_task.enums.Authority;
import uz.ilmnajot.revolution_task.template.AbsEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "roles")
public class Role extends AbsEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private Authority authorities;
}
