package uz.ilmnajot.revolution_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.revolution_task.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
