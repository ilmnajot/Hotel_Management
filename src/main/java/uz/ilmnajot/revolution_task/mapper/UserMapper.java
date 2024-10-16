package uz.ilmnajot.revolution_task.mapper;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.model.request.UserRequest;
import uz.ilmnajot.revolution_task.model.response.UserResponse;
import uz.ilmnajot.revolution_task.template.UserSession;

public interface UserMapper {

    public User toUser(UserRequest request);
    public UserRequest toRequest(User user);
    public UserResponse toResponse(User user);
}
