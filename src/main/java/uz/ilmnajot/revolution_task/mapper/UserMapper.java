package uz.ilmnajot.revolution_task.mapper;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.model.request.UserRequest;

public interface UserMapper {

    public User toUser(UserRequest request);
    public UserRequest toRequest(User user);
}
