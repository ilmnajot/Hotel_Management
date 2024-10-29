package uz.ilmnajot.revolution_task.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;
import uz.ilmnajot.revolution_task.entity.Comment;
import uz.ilmnajot.revolution_task.enums.RoomCategory;
import uz.ilmnajot.revolution_task.enums.RoomStatus;
import uz.ilmnajot.revolution_task.enums.RoomType;

import java.math.BigDecimal;
import java.util.List;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {

    private Long id;

    private int roomNumber;

    private BigDecimal price;

    private RoomStatus status;

    private RoomCategory category;

    private RoomType roomType;

//    private List<CommentDto> commentDtoList;
}
