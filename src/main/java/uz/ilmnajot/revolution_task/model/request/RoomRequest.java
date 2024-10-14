package uz.ilmnajot.revolution_task.model.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.revolution_task.entity.Comment;
import uz.ilmnajot.revolution_task.enums.RoomCategory;
import uz.ilmnajot.revolution_task.enums.RoomType;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class RoomRequest {


    private int roomNumber;

    private int floor;


    private Boolean available;

    private LocalDateTime bookedDate;

    private RoomCategory category;

    private RoomType roomType;

//    private List<Comment> comments;
}
