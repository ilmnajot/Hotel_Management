package uz.ilmnajot.revolution_task.template.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.ilmnajot.revolution_task.entity.auth.Role;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.enums.Authority;
import uz.ilmnajot.revolution_task.enums.RoleType;
import uz.ilmnajot.revolution_task.repository.RoleRepository;
import uz.ilmnajot.revolution_task.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Value("${spring.sql.init.mode}")
    private String mode;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        List<Authority> authorityList = Arrays.asList(Authority.values());

        if (mode.equals("always")) {
            Role role = new Role();
            role.setName("USER");
            role.setRoleType(RoleType.USER);
            roleRepository.save(role);

            User user = new User();
            user.setUsername("user@gmail.com");
            user.setPassword("user");
            user.setAuthorities(authorityList);
            user.setEnabled(true);
            userRepository.save(user);
        }
    }
}
