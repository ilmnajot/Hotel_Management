package uz.ilmnajot.revolution_task.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.revolution_task.enums.RoomCategory;
import uz.ilmnajot.revolution_task.enums.RoomType;
import uz.ilmnajot.revolution_task.template.AbsEntity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "rooms")
@Builder
public class Room extends AbsEntity {

    @Column(unique = true)
    private int roomNumber;

    private int floor;


    private Boolean available;

    private LocalDateTime bookedDate;

    @Enumerated(EnumType.STRING)
    private RoomCategory category;

    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;

    @OneToMany
    private List<Comment> comments;
}
