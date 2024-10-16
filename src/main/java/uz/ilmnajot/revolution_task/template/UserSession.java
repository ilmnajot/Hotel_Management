package uz.ilmnajot.revolution_task.template;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.mapper.UserMapper;
import uz.ilmnajot.revolution_task.model.request.UserRequest;
import uz.ilmnajot.revolution_task.model.response.UserResponse;

@Component
public class UserSession {


    private final UserMapper userMapper;

    public UserSession(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public UserResponse getUser() {
        UserResponse userResponse = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            userResponse = userMapper.toResponse((User) authentication.getPrincipal());
        }
        return userResponse;
    }
}
