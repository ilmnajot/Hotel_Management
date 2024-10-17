package uz.ilmnajot.revolution_task.payload.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.revolution_task.entity.Reservation;

import java.time.LocalDate;

@Setter
@Getter
public class ReservationResponse {

    private Long userId;
    private Long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public ReservationResponse toReservationResponse(Reservation reservation) {
        ReservationResponse response = new ReservationResponse();
        response.setUserId(userId);
        response.setRoomId(roomId);
        response.setCheckInDate(checkInDate);
        response.setCheckOutDate(checkOutDate);
        return response;
    }



}
