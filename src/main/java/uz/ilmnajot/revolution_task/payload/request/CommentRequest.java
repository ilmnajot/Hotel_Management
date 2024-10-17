package uz.ilmnajot.revolution_task.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequest {

    private String text;

    private float score=4.5f;

    private Long userId;

    private Long roomId;
}
