package uz.ilmnajot.revolution_task.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.entity.Comment;
import uz.ilmnajot.revolution_task.entity.Hotel;
import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;
import uz.ilmnajot.revolution_task.payload.request.CommentRequest;
import uz.ilmnajot.revolution_task.payload.response.CommentResponse;
import uz.ilmnajot.revolution_task.repository.CommentRepository;
import uz.ilmnajot.revolution_task.repository.HotelRepository;
import uz.ilmnajot.revolution_task.repository.RoomRepository;
import uz.ilmnajot.revolution_task.repository.UserRepository;
import uz.ilmnajot.revolution_task.service.interfaces.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final HotelRepository hotelRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public CommentServiceImpl(CommentRepository commentRepository, HotelRepository hotelRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.commentRepository = commentRepository;
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }


    @Override
    public ApiResponse commentToHotel(Long hotelId, CommentRequest request) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()
                -> new NotFoundException("hotel not found", HttpStatus.BAD_REQUEST));
        User user = userRepository.findById(request.getUserId()).orElseThrow(()
                -> new NotFoundException("user not found", HttpStatus.BAD_REQUEST));
        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setScore(request.getScore());
        comment.setUsers(user);
        comment.setHotel(hotel);
        Comment savedComment = commentRepository.save(comment);
        CommentResponse commentResponse = new CommentResponse().toCommentResponse(savedComment);
        return new ApiResponse(true, "success", commentResponse);
    }

    @Override
    public ApiResponse commentToRoom(Long roomId, CommentRequest request) {
        Room room = roomRepository.findById(roomId).orElseThrow(()
                -> new NotFoundException("room not found", HttpStatus.BAD_REQUEST));
        User user = userRepository.findById(request.getUserId()).orElseThrow(()
                -> new NotFoundException("user not found", HttpStatus.BAD_REQUEST));
        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setScore(request.getScore());
        comment.setUsers(user);
        comment.setRoom(room);
        Comment savedComment = commentRepository.save(comment);
        CommentResponse commentResponse = new CommentResponse().toCommentResponse(savedComment);
        return new ApiResponse(true, "success", commentResponse);
    }
}