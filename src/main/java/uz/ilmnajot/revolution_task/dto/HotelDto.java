package uz.ilmnajot.revolution_task.dto;

import jakarta.annotation.security.DenyAll;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;
import uz.ilmnajot.revolution_task.entity.Comment;
import uz.ilmnajot.revolution_task.entity.Room;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDto {

    private Long id;
    private String name;
    private String description;
    private String address;
    private String city;
    private String country;
    private String phone;
//    private List<CommentDto> commentDtoList;
    private List<RoomDto> roomDtoList;
}
