package uz.ilmnajot.revolution_task.payload.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.enums.RoomCategory;
import uz.ilmnajot.revolution_task.enums.RoomStatus;
import uz.ilmnajot.revolution_task.enums.RoomType;
import uz.ilmnajot.revolution_task.payload.request.RoomRequest;

import java.math.BigDecimal;

@Setter
@Getter
public class RoomResponse {


    private Long id;

    private int roomNumber;

    private BigDecimal price;

    private RoomStatus status;

    private RoomCategory category;

    private RoomType roomType;

    public RoomResponse toRoomResponse(Room room){
        RoomResponse response = new RoomResponse();
        response.setId(room.getId());
        response.setRoomNumber(room.getRoomNumber());
        response.setPrice(room.getPrice());
        response.setStatus(room.getStatus());
        response.setCategory(room.getCategory());
        response.setRoomType(room.getRoomType());
        return response;
    }
}
