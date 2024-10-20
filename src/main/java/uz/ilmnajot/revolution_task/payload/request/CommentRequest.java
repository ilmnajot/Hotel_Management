package uz.ilmnajot.revolution_task.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CommentRequest {

    @NotNull(message = "Comment text cannot be null")
    @Size(min = 1, max = 500, message = "Comment text must be between 1 and 500 characters")
    private String text;

    @Min(value = 1, message = "Score must be at least 1")
    @Max(value = 5, message = "Score cannot be more than 5")
    private float score = 4.5f;  // Default score

//    @NotNull(message = "User ID cannot be null")
//    private Long userId;


//    private Long roomId;
//
//    private Long hotelId;
}
