package uz.ilmnajot.revolution_task.entity.auth;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.revolution_task.enums.Authority;
import uz.ilmnajot.revolution_task.enums.RoleType;
import uz.ilmnajot.revolution_task.template.AbsEntity;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "roles")
public class Role extends AbsEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

//        @Enumerated(EnumType.STRING)
//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<Authority> authorities;

}
