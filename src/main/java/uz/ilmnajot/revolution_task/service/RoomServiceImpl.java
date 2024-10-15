package uz.ilmnajot.revolution_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.exception.AlreadyExistsException;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.mapper.RoomMapper;
import uz.ilmnajot.revolution_task.model.request.RoomRequest;
import uz.ilmnajot.revolution_task.repository.RoomRepository;
import uz.ilmnajot.revolution_task.service.interfaces.RoomService;
import uz.ilmnajot.revolution_task.template.common.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public ApiResponse addRoom(RoomRequest request) {
        Optional<Room> optionalRoom = roomRepository.findByRoomNumber(request.getRoomNumber());
        if (optionalRoom.isPresent()) {
            throw new AlreadyExistsException(HttpStatus.BAD_REQUEST, "Room already added");
        }
        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setPrice(request.getPrice());
        room.setStatus(request.getStatus());
        room.setCategory(request.getCategory());
        room.setRoomType(request.getRoomType());
        Room addedRoom = roomRepository.save(room);
        RoomRequest mapperRequest = roomMapper.toRequest(addedRoom);
        return new ApiResponse(true, "Successfully added room", mapperRequest, HttpStatus.CREATED);
    }

    @Override
    public ApiResponse updateRoom(Long roomId, RoomRequest request) {
        Room room = roomRepository.findById(roomId).orElseThrow(()
                -> new NotFoundException("room not found", HttpStatus.BAD_REQUEST));
        room.setRoomNumber(request.getRoomNumber());
        room.setPrice(request.getPrice());
//        room.setStatus(RoomStatus.AVAILABLE);
        room.setCategory(request.getCategory());
        room.setRoomType(request.getRoomType());
        Room save = roomRepository.save(room);
        RoomRequest mapperRequest = roomMapper.toRequest(save);
        return new ApiResponse(true, "updated", mapperRequest, HttpStatus.OK);
    }

    @Override
    public ApiResponse getRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("room not found", HttpStatus.BAD_REQUEST));
        RoomRequest mapperRequest = roomMapper.toRequest(room);
        return new ApiResponse(true, "Room: ", mapperRequest, HttpStatus.OK);
    }

    @Override
    public ApiResponse getRooms(int page, int size) {
        Page<Room> roomPage = roomRepository.findAll(PageRequest.of(page, size));
        List<RoomRequest> list = roomPage.stream().map(roomMapper::toRequest).toList();
        return new ApiResponse(true, "success", list, HttpStatus.OK);
    }

    @Override
    public ApiResponse removeRoom(Long roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setDisabled(true);
            roomRepository.save(room);
            return new ApiResponse(true, "Successfully deleted", HttpStatus.OK);
        }
        throw new NotFoundException("room not found", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ApiResponse getAvailableRooms(int page, int size) {
        Page<Room> availableRooms = roomRepository.findAllByDisabledFalse(PageRequest.of(page, size));
        List<RoomRequest> list = availableRooms
                .stream()
                .map(roomMapper::toRequest)
                .toList();
        return new ApiResponse(true, "success", list, HttpStatus.OK);
    }

    @Override
    public ApiResponse getRemovedRooms(int page, int size) {
        Page<Room> removedRooms = roomRepository.findAllByDisabledTrue(PageRequest.of(page, size));
        List<RoomRequest> roomList = removedRooms
                .stream()
                .map(roomMapper::toRemovedRoomRequest)
                .toList();
        return new ApiResponse(true, "success", roomList, HttpStatus.OK);
    }
}
