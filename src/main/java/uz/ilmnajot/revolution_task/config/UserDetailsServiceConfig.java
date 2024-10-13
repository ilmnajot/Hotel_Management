package uz.ilmnajot.revolution_task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.repository.UserRepository;
import uz.ilmnajot.revolution_task.service.interfaces.CustomUserDetailsService;

@Configuration
public class UserDetailsServiceConfig {

    private final UserRepository userRepository;

    public UserDetailsServiceConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByUsername(username).orElseThrow(()
                -> new NotFoundException("User not found"));
    }
}
