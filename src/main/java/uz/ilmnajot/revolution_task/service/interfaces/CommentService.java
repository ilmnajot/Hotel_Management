package uz.ilmnajot.revolution_task.service.interfaces;

import uz.ilmnajot.revolution_task.model.common.ApiResponse;

public interface CommentService {
    ApiResponse commentToHotel(Long hotelId);
}
