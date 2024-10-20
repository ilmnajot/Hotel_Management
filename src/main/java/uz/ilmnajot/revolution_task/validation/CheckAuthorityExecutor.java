package uz.ilmnajot.revolution_task.validation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.exception.ForbiddenException;

@Component
@Aspect
public class CheckAuthorityExecutor {

    @Before(value = "@annotation(checkAuthority)")
    public void beforeCheckPermission(CheckAuthority checkAuthority) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist = false;

        System.out.println("Required authority: " + checkAuthority.value());
        for (GrantedAuthority authority : user.getAuthorities()) {
            System.out.println("User hase Authority: " + authority.getAuthority());
            if (authority.getAuthority().equals(checkAuthority.value())) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new ForbiddenException(checkAuthority.value(), HttpStatus.FORBIDDEN);
        }

    }
}
