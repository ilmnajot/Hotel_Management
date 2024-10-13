package uz.ilmnajot.revolution_task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;
import uz.ilmnajot.revolution_task.enums.RoomCategory;
import uz.ilmnajot.revolution_task.template.AbsEntity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "rooms")
@Builder
public class Room extends AbsEntity {

    private int roomNumber;

    private int floor;

    @Enumerated(EnumType.STRING)
    private RoomCategory category;

    private float roomScore = 4.5f;
    @OneToMany
    private List<Comment> comments;
}
