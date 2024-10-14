package uz.ilmnajot.revolution_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.exception.AlreadyExistsException;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.model.request.RoomRequest;
import uz.ilmnajot.revolution_task.repository.RoomRepository;
import uz.ilmnajot.revolution_task.service.interfaces.RoomService;
import uz.ilmnajot.revolution_task.template.common.ApiResponse;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public ApiResponse addRoom(RoomRequest request) {
        Optional<Room> optionalRoom = roomRepository.findByRoomNumber(request.getRoomNumber());
        if (optionalRoom.isPresent()) {
            throw new AlreadyExistsException(HttpStatus.BAD_REQUEST, "Room already added");
        }
        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setFloor(request.getFloor());
        room.setAvailable(false);
        room.setBookedDate(request.getBookedDate());
        room.setCategory(request.getCategory());
        room.setRoomType(request.getRoomType());

        return null;
    }
}
