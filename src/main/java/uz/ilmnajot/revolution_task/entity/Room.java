package uz.ilmnajot.revolution_task.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.revolution_task.enums.RoomCategory;
import uz.ilmnajot.revolution_task.enums.RoomStatus;
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

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @Enumerated(EnumType.STRING)
    private RoomCategory category;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Room(int roomNumber, BigDecimal price, RoomStatus status, RoomCategory category, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.status = status;
        this.category = category;
        this.roomType = roomType;
    }
}
