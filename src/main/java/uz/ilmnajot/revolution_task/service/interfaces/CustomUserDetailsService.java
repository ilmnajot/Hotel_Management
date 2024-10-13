package uz.ilmnajot.revolution_task.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
    public UserDetails loadUserByUsername(String username);
}
