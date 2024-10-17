package uz.ilmnajot.revolution_task.payload.request;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.revolution_task.enums.RoomCategory;
import uz.ilmnajot.revolution_task.enums.RoomStatus;
import uz.ilmnajot.revolution_task.enums.RoomType;

import java.math.BigDecimal;

@Setter
@Getter
public class RoomRequest {


    private int roomNumber;

    private BigDecimal price;

    private RoomStatus status;

    private RoomCategory category;

    private RoomType roomType;

//    private List<Comment> comments;
}
