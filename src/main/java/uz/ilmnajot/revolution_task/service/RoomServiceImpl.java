package uz.ilmnajot.revolution_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.enums.RoomStatus;
import uz.ilmnajot.revolution_task.exception.AlreadyExistsException;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.payload.request.RoomRequest;
import uz.ilmnajot.revolution_task.payload.response.RoomResponse;
import uz.ilmnajot.revolution_task.repository.RoomRepository;
import uz.ilmnajot.revolution_task.service.interfaces.RoomService;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        room.setPrice(request.getPrice());
        room.setStatus(request.getStatus());
        room.setCategory(request.getCategory());
        room.setRoomType(request.getRoomType());
        Room addedRoom = roomRepository.save(room);
        RoomResponse roomResponse = new RoomResponse().toRoomResponse(addedRoom);
//        RoomRequest mapperRequest = roomMapper.toRequest(addedRoom);
        return new ApiResponse(true, "Successfully added room", roomResponse, HttpStatus.CREATED);
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
        RoomResponse roomResponse = new RoomResponse().toRoomResponse(save);
//        RoomRequest mapperRequest = roomMapper.toRequest(save);
        return new ApiResponse(true, "updated", roomResponse, HttpStatus.OK);
    }

    @Override
    public ApiResponse getRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException("room not found", HttpStatus.BAD_REQUEST));
//        RoomRequest mapperRequest = roomMapper.toRequest(room);

        RoomResponse roomResponse = new RoomResponse().toRoomResponse(room);
        return new ApiResponse(true, "Room: ", roomResponse, HttpStatus.OK);
    }

    @Override
    public ApiResponse getRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        if(roomPage.isEmpty()){
            throw new NotFoundException("rooms not found", HttpStatus.NOT_FOUND);
        }
        List<RoomResponse> list = roomPage
                .stream()
                .map(new RoomResponse()::toRoomResponse)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("rooms", list);
        response.put("currentPage", roomPage.getNumber());
        response.put("all pages", roomPage.getTotalElements());
        return new ApiResponse(true, "success", list, HttpStatus.OK);
    }

    @Override
    public ApiResponse removeRoom(Long roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.setDisabled(true);
            room.setStatus(RoomStatus.MAINTENANCE);
            roomRepository.save(room);
            return new ApiResponse(true, "Successfully deleted", HttpStatus.OK);
        }
        throw new NotFoundException("room not found", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ApiResponse getAvailableRooms(int page, int size) {
        Page<Room> availableRooms = roomRepository.findAllByDisabledFalse(PageRequest.of(page, size));
        List<RoomResponse> list = availableRooms
                .stream()
                .map(new RoomResponse()::toRoomResponse)
                .toList();
        return new ApiResponse(true, "success", list, HttpStatus.OK);
    }

    @Override
    public ApiResponse getRemovedRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Room> removedRooms = roomRepository.findAllByDisabledTrue(pageable);
        if (removedRooms.isEmpty()) {
            throw new NotFoundException("rooms not found", HttpStatus.OK);
        }
        List<RoomResponse> roomList = removedRooms
                .stream()
                .map(new RoomResponse()::toRoomResponse)
                .toList();
        return new ApiResponse(true, "success", roomList, HttpStatus.OK);
    }

    @Override
    public ApiResponse getBookedDays(int page, int size) {
        return null;

    }
}
