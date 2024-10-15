package uz.ilmnajot.revolution_task.template.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String mode;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {

        List<Authority> authorityListAdmin = Arrays.asList(
                Authority.values());

        List<Authority> authorityListManager = Arrays.asList(
                Authority.ADD_ROOM,
                Authority.BOOK_ROOM,
                Authority.EDIT_ROOM,
                Authority.GET_ROOM,
                Authority.GET_ROOMS);

        List<Authority> authorityListUser = Arrays.asList(
                Authority.GET_ROOM, Authority.GET_ROOMS);

        if (mode.equals("always")) {

            Role roleUser = new Role();
            roleUser.setName("USER");
            roleUser.setRoleType(RoleType.USER);
            roleRepository.save(roleUser);


            Role roleAdmin = new Role();
            roleAdmin.setName("ADMIN");
            roleAdmin.setRoleType(RoleType.ADMIN);
            roleRepository.save(roleAdmin);


            Role roleManager = new Role();
            roleManager.setName("MANAGER");
            roleManager.setRoleType(RoleType.MANAGER);
            roleRepository.save(roleManager);


            User user = new User();
            user.setUsername("user@gmail.com");
            user.setPassword(passwordEncoder.encode("user"));
            user.setAuthorities(authorityListUser);
            user.setRole(roleUser);
            user.setEnabled(true);
            userRepository.save(user);


            User admin = new User();
            admin.setUsername("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setAuthorities(authorityListAdmin);
            admin.setRole(roleAdmin);
            admin.setEnabled(true);
            userRepository.save(admin);

            User manager = new User();
            manager.setUsername("manager@gmail.com");
            manager.setPassword(passwordEncoder.encode("manager"));
            manager.setAuthorities(authorityListManager);
            manager.setRole(roleManager);
            manager.setEnabled(true);
            userRepository.save(manager);


        }
    }
}
