package uz.ilmnajot.revolution_task.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import uz.ilmnajot.revolution_task.dto.RoomDto;
import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomMapperImpl implements RoomMapper {

    private final RoomRepository roomRepository;

    public RoomMapperImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room toRoomEntity(RoomDto dto) {
        return Room
                .builder()
                .roomNumber(dto.getRoomNumber())
                .price(dto.getPrice())
                .status(dto.getStatus())
                .category(dto.getCategory())
                .roomType(dto.getRoomType())
                .build();
    }

    public RoomDto toRoomDto(Room room) {
        return RoomDto
                .builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .price(room.getPrice())
                .status(room.getStatus())
                .category(room.getCategory())
                .roomType(room.getRoomType())
                .build();
    }

    public List<RoomDto> toRoomDtoList(Page<Room> rooms) {
        List<RoomDto> dtoList = new ArrayList<>();
        for (Room room : rooms) {
            dtoList.add(toRoomDto(room));
        }
        return dtoList;
    }
    public List<RoomDto> toRoomDtoList(List<Room> rooms) {
        List<RoomDto> dtoList = new ArrayList<>();
        for (Room room : rooms) {
            dtoList.add(toRoomDto(room));
        }
        return dtoList;
    }

    public Room toUpdateEntity(RoomDto roomDto, Room room) {
        if (roomDto.getRoomNumber() != 0) {
            room.setRoomNumber(roomDto.getRoomNumber());
        }
        if (roomDto.getPrice() != null) {
            room.setPrice(roomDto.getPrice());
        }
        if (roomDto.getStatus() != null) {
            room.setStatus(roomDto.getStatus());
        }
        if (roomDto.getCategory() != null) {
            room.setCategory(roomDto.getCategory());
        }
        if (roomDto.getRoomType() != null) {
            room.setRoomType(roomDto.getRoomType());
        }
        return room;
    }

}
