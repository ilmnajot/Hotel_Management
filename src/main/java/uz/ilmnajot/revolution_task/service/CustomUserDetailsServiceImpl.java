package uz.ilmnajot.revolution_task.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.repository.UserRepository;
import uz.ilmnajot.revolution_task.service.interfaces.CustomUserDetailsService;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()
                -> new NotFoundException("User not found"));
    }
}
