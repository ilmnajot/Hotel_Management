package uz.ilmnajot.revolution_task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.revolution_task.entity.Reservation;
import uz.ilmnajot.revolution_task.entity.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomNumber(int roomNumber);

    Page<Room> findAllByDisabledFalse(Pageable pageable);

    Page<Room> findAllByDisabledTrue(Pageable pageable);

    Optional<Room> findByIdAndDisabledFalse(Long id);

    @Query("SELECT r FROM reservation r WHERE r.room.id = :roomId " +
            "AND (r.checkInDate BETWEEN :startDate AND :endDate " +
            "OR r.checkOutDate BETWEEN :startDate AND :endDate)")
    List<Reservation> findReservationsForRoomInPeriod(@Param("roomId") Long roomId,
                                                      @Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate); // not done yet

    @Query("select r from rooms as r order by r.price desc")
    List<Room> findTopExpensiveRooms(Pageable pageable);

}
