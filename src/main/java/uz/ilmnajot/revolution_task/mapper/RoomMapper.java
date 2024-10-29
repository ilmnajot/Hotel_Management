package uz.ilmnajot.revolution_task.mapper;

import org.springframework.data.domain.Page;
import uz.ilmnajot.revolution_task.dto.RoomDto;
import uz.ilmnajot.revolution_task.entity.Room;

import java.util.List;

public interface RoomMapper {

    Room toRoomEntity(RoomDto dto);

    RoomDto toRoomDto(Room room);

    Room toUpdateEntity(RoomDto roomDto, Room room);

    List<RoomDto> toRoomDtoList(Page<Room> rooms);

    List<RoomDto> toRoomDtoList(List<Room> rooms);
}
