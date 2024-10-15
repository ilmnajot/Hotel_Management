package uz.ilmnajot.revolution_task.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.revolution_task.entity.Room;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomNumber(int roomNumber);

    Page<Room> findAllByDisabledFalse(Pageable pageable);
    Page<Room> findAllByDisabledTrue(Pageable pageable);
}
