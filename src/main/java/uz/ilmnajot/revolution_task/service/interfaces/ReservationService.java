package uz.ilmnajot.revolution_task.service.interfaces;

import uz.ilmnajot.revolution_task.payload.common.ApiResponse;

import java.time.LocalDate;

public interface ReservationService {
    ApiResponse bookRoom(Long userId, Long roomId, LocalDate checkInDate, LocalDate checkOutDate);

    ApiResponse getInfo(Long userId);
}
