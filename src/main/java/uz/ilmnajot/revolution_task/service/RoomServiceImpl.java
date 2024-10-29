package uz.ilmnajot.revolution_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.dto.RoomDto;
import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.enums.RoomStatus;
import uz.ilmnajot.revolution_task.exception.AlreadyExistsException;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.mapper.RoomMapper;
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

    private final RoomMapper roomMapper;

    @Override
    public ApiResponse addRoom(RoomDto request) {
        Optional<Room> optionalRoom = roomRepository.findByRoomNumber(request.getRoomNumber());
        if (optionalRoom.isPresent()) {
            throw new AlreadyExistsException(HttpStatus.BAD_REQUEST, "Room already added");
        }
        Room roomEntity = roomMapper.toRoomEntity(request);
        Room addedRoom = roomRepository.save(roomEntity);
        RoomDto mapperRequest = roomMapper.toRoomDto(addedRoom);
        return new ApiResponse(true, "Successfully added room", mapperRequest, HttpStatus.CREATED);
    }

    @Override
    public ApiResponse updateRoom(Long roomId, RoomDto request) {
        Room room = roomRepository.findById(roomId).orElseThrow(()
                -> new NotFoundException("room not found", HttpStatus.BAD_REQUEST));

        room = roomMapper.toUpdateEntity(request, room);

        Room updatedRoom = this.roomRepository.save(room);
        RoomDto roomDto = roomMapper.toRoomDto(updatedRoom);
        return new ApiResponse(true, "updated", roomDto, HttpStatus.OK);
    }

    @Override
    public ApiResponse getRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(()
                -> new NotFoundException("room not found", HttpStatus.BAD_REQUEST));
        RoomDto roomDto = roomMapper.toRoomDto(room);
        return new ApiResponse(true, "Room: ", roomDto, HttpStatus.OK);
    }

    @Override
    public ApiResponse getRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        if (roomPage.isEmpty()) {
            throw new NotFoundException("rooms not found", HttpStatus.NOT_FOUND);
        }
        List<RoomDto> roomDtoList = roomMapper.toRoomDtoList(roomPage);

        Map<String, Object> response = new HashMap<>();
        response.put("rooms", roomDtoList);
        response.put("currentPage", roomPage.getNumber());
        response.put("all pages", roomPage.getTotalElements());
        return new ApiResponse(true, "success", response, HttpStatus.OK);
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
        List<RoomDto> roomDtoList = roomMapper.toRoomDtoList(availableRooms);
        return new ApiResponse(true, "success", roomDtoList, HttpStatus.OK);
    }

    @Override
    public ApiResponse getRemovedRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Room> removedRooms = roomRepository.findAllByDisabledTrue(pageable);
        if (removedRooms.isEmpty()) {
            throw new NotFoundException("rooms not found", HttpStatus.OK);
        }
        List<RoomDto> roomDtoList = roomMapper.toRoomDtoList(removedRooms);
        return new ApiResponse(true, "success", roomDtoList, HttpStatus.OK);
    }

    @Override
    public ApiResponse getBookedDays(int page, int size) {
        return null;

    }

    @Override
    public ApiResponse getExpensiveRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Room> topExpensiveRooms = roomRepository.findTopExpensiveRooms(pageable);
        List<RoomDto> roomDtoList = roomMapper.toRoomDtoList(topExpensiveRooms);
        return new ApiResponse(true, "success", roomDtoList, HttpStatus.OK);
    }
}
