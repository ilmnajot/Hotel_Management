package uz.ilmnajot.revolution_task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
    private User user;

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
