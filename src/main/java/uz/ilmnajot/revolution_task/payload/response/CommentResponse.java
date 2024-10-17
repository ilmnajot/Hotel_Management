package uz.ilmnajot.revolution_task.payload.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.revolution_task.entity.Comment;

@Setter
@Getter
public class CommentResponse {

    private Long id;

    private String text;

    private float score=4.5f;

    private Long userId;


    public CommentResponse toCommentResponse(Comment comment){
        CommentResponse response = new CommentResponse();
        response.setText(comment.getText());
        response.setScore(comment.getScore());
        response.setUserId(comment.getUsers().getId());
        return response;
    }
}
