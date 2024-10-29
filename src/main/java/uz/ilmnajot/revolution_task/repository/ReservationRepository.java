package uz.ilmnajot.revolution_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.revolution_task.entity.Reservation;
import uz.ilmnajot.revolution_task.payload.response.UserReservationInfoResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM reservation  r WHERE r.room.id = :roomId AND " +
            "(r.checkInDate <= :checkOutDate AND r.checkOutDate >= :checkInDate)")
    List<Reservation> findOverlappingReservations(@Param("roomId") Long roomId,
                                                  @Param("checkInDate") LocalDate checkInDate,
                                                  @Param("checkOutDate") LocalDate checkOutDate);

    @Query("SELECT new uz.ilmnajot.revolution_task.payload.response.UserReservationInfoResponse(res.id, u.id, r.id, res.checkInDate, res.checkOutDate, r.status) " +
            "from reservation as res " +
            "join res.user as u " +
            "join res.room as r" +
            " where u.id=:userId" )
    List<UserReservationInfoResponse> getUserReservationInfo(@Param("userId") Long userId);
}
