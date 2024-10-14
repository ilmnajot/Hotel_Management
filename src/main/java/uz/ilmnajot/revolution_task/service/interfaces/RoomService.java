package uz.ilmnajot.revolution_task.service.interfaces;

import uz.ilmnajot.revolution_task.model.request.RoomRequest;
import uz.ilmnajot.revolution_task.template.common.ApiResponse;

public interface RoomService {
    ApiResponse addRoom(RoomRequest request);
}
