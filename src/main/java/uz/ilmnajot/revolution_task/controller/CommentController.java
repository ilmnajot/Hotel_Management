package uz.ilmnajot.revolution_task.controller;

import org.hibernate.annotations.Check;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.revolution_task.entity.Comment;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;
import uz.ilmnajot.revolution_task.payload.request.CommentRequest;
import uz.ilmnajot.revolution_task.payload.request.RoomRequest;
import uz.ilmnajot.revolution_task.service.interfaces.CommentService;
import uz.ilmnajot.revolution_task.validation.CheckAuthority;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @CheckAuthority("POST_COMMENT")
    @PostMapping("/postComment/{hotelId}")
    public HttpEntity<ApiResponse> postComment(@PathVariable("hotelId") Long hotelId,@RequestBody CommentRequest request) {
        ApiResponse apiResponse = commentService.commentToHotel(hotelId, request);
        return ResponseEntity.ok(apiResponse);
    }

    @CheckAuthority("POST_COMMENT")
    @PostMapping("/postComment/{roomId}")
    public HttpEntity<ApiResponse> postCommentRoom(@PathVariable("roomId") Long roomId,@RequestBody CommentRequest request) {
        ApiResponse apiResponse = commentService.commentToRoom(roomId, request);
        return ResponseEntity.ok(apiResponse);
    }
}
