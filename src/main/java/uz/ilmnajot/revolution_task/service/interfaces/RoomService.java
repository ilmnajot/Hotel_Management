package uz.ilmnajot.revolution_task.service.interfaces;

import uz.ilmnajot.revolution_task.model.request.RoomRequest;
import uz.ilmnajot.revolution_task.template.common.ApiResponse;

public interface RoomService {
    ApiResponse addRoom(RoomRequest request);

    ApiResponse updateRoom(Long roomId, RoomRequest request);

    ApiResponse getRoom(Long roomId);

    ApiResponse getRooms(int page, int size);

    ApiResponse removeRoom(Long roomId);

    ApiResponse getAvailableRooms(int page, int size);

    ApiResponse getRemovedRooms(int page, int size);
}
