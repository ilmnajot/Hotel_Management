package uz.ilmnajot.revolution_task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.template.AbsEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "comment")
@Setter
@Getter
@Builder
public class Comment extends AbsEntity {

    private String text;

    private float score=4.5f;

    @ManyToOne
    private User users;

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private Room room;
}
