package uz.ilmnajot.revolution_task.mapper;

import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.model.request.RoomRequest;

public interface RoomMapper {
    public Room toEntity(RoomRequest request);
    public RoomRequest toRemovedRoomRequest(Room room);
    public RoomRequest toRequest(Room room);

}
