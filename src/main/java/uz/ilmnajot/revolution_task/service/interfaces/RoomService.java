package uz.ilmnajot.revolution_task.service.interfaces;

import uz.ilmnajot.revolution_task.dto.RoomDto;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;

public interface RoomService {
    ApiResponse addRoom(RoomDto request);

    ApiResponse updateRoom(Long roomId, RoomDto request);

    ApiResponse getRoom(Long roomId);

    ApiResponse getRooms(int page, int size);

    ApiResponse removeRoom(Long roomId);

    ApiResponse getAvailableRooms(int page, int size);

    ApiResponse getRemovedRooms(int page, int size);

    ApiResponse getBookedDays(int page, int size);

    ApiResponse getExpensiveRooms(int page, int size);
}
