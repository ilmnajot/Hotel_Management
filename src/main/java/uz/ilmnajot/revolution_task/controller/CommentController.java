package uz.ilmnajot.revolution_task.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.revolution_task.model.common.ApiResponse;
import uz.ilmnajot.revolution_task.service.interfaces.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/postComment/{hotelId}")
    public HttpEntity<ApiResponse> postComment(@PathVariable("hotelId") Long hotelId) {
        commentService.commentToHotel(hotelId);

    }
}
