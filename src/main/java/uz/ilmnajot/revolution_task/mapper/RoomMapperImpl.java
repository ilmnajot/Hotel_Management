package uz.ilmnajot.revolution_task.mapper;

import org.springframework.stereotype.Component;
import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.enums.RoomStatus;
import uz.ilmnajot.revolution_task.model.request.RoomRequest;

@Component
public class RoomMapperImpl implements RoomMapper {

    public Room toEntity(RoomRequest request){
        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setPrice(request.getPrice());
        room.setStatus(request.getStatus());
        room.setCategory(request.getCategory());
        room.setRoomType(request.getRoomType());
        return room;
    }

    public RoomRequest toRequest(Room room){
        RoomRequest roomRequest = new RoomRequest();
        roomRequest.setRoomNumber(room.getRoomNumber());
        roomRequest.setPrice(room.getPrice());
        roomRequest.setStatus(room.getStatus());
        roomRequest.setCategory(room.getCategory());
        roomRequest.setRoomType(room.getRoomType());
        return roomRequest;
    }

    public RoomRequest toRemovedRoomRequest(Room room){
        RoomRequest roomRequest = new RoomRequest();
        roomRequest.setRoomNumber(room.getRoomNumber());
        roomRequest.setPrice(room.getPrice());
        roomRequest.setStatus(RoomStatus.MAINTENANCE);
        roomRequest.setCategory(room.getCategory());
        roomRequest.setRoomType(room.getRoomType());
        return roomRequest;
    }

}
