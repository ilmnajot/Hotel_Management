package uz.ilmnajot.revolution_task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;
import uz.ilmnajot.revolution_task.template.AbsEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "hotel")
@Builder
@Setter
@Getter
public class Hotel extends AbsEntity {

    private String name;
    private String description;
    private String address;
    private String city;
    private String country;
    private String phone;

    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<Comment> comments;

    @OneToMany
    @JoinColumn(name = "room_id")
    private List<Room> rooms;
}
