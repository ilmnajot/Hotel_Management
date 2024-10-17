package uz.ilmnajot.revolution_task.service.interfaces;

import uz.ilmnajot.revolution_task.payload.common.ApiResponse;
import uz.ilmnajot.revolution_task.payload.request.CommentRequest;

public interface CommentService {
    ApiResponse commentToHotel(Long hotelId, CommentRequest comment);

    ApiResponse commentToRoom(Long roomId, CommentRequest request);
}
