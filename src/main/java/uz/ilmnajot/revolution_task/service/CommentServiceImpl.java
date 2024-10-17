package uz.ilmnajot.revolution_task.service;

import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.model.common.ApiResponse;
import uz.ilmnajot.revolution_task.repository.CommentRepository;
import uz.ilmnajot.revolution_task.service.interfaces.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public ApiResponse commentToHotel(Long hotelId) {

        return null;
    }
}
