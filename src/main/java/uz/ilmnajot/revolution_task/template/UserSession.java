package uz.ilmnajot.revolution_task.template;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.payload.response.UserResponse;

@Component
public class UserSession {


    private final UserResponse userResponse;

    public UserSession(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public UserResponse getUser() {
        UserResponse userResponse = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            userResponse = this.userResponse.toUserResponse((User) authentication.getPrincipal());
        }
        return userResponse;
    }
}
