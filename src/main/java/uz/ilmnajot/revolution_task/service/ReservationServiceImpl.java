package uz.ilmnajot.revolution_task.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.entity.Reservation;
import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.enums.RoomStatus;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.payload.response.UserReservationInfoResponse;
import uz.ilmnajot.revolution_task.repository.ReservationRepository;
import uz.ilmnajot.revolution_task.repository.RoomRepository;
import uz.ilmnajot.revolution_task.repository.UserRepository;
import uz.ilmnajot.revolution_task.service.interfaces.ReservationService;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public ApiResponse bookRoom(Long userId,
                                Long roomId,
                                LocalDate checkInDate,
                                LocalDate checkOutDate) {
        Room room = roomRepository.findById(roomId).orElseThrow(()
                -> new NotFoundException("room not found", HttpStatus.BAD_REQUEST));
        User user = userRepository.findById(userId).orElseThrow(()
                -> new NotFoundException("user not found", HttpStatus.BAD_REQUEST));
        if (!isRoomAvailable(roomId, checkInDate, checkOutDate)) {
            throw new NotFoundException("room not available", HttpStatus.BAD_REQUEST);
        }
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);
        reservation.setTotalPrice(calculateTotalPrice(room.getPrice(), checkInDate, checkOutDate));
        reservationRepository.save(reservation);

        room.setStatus(RoomStatus.BOOKED);
        roomRepository.save(room);
        return new ApiResponse(true, "booked", HttpStatus.OK);
    }

    @Override
    public ApiResponse getInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
        List<UserReservationInfoResponse> userReservationInfo = reservationRepository.getUserReservationInfo(user.getId());
        return new ApiResponse(true, "success", userReservationInfo, HttpStatus.OK);
    }


    public boolean isRoomAvailable(Long roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Reservation> reservationList = reservationRepository.findOverlappingReservations(roomId, checkInDate, checkOutDate);
        return reservationList.isEmpty();
    }

    public BigDecimal calculateTotalPrice(BigDecimal roomPrice, LocalDate checkInDate, LocalDate checkOutDate) {
        long daysBetween = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return roomPrice.multiply(BigDecimal.valueOf(daysBetween));

    }
}
