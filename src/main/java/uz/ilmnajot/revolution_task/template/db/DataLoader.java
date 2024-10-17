package uz.ilmnajot.revolution_task.template.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.revolution_task.entity.Hotel;
import uz.ilmnajot.revolution_task.entity.Room;
import uz.ilmnajot.revolution_task.entity.auth.Role;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.enums.*;
import uz.ilmnajot.revolution_task.repository.HotelRepository;
import uz.ilmnajot.revolution_task.repository.RoleRepository;
import uz.ilmnajot.revolution_task.repository.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HotelRepository hotelRepository;

    @Value("${spring.sql.init.mode}")
    private String mode;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, HotelRepository hotelRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.hotelRepository = hotelRepository;
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


            List<Room> roomList = Arrays.asList(
                    new Room(1, null, RoomStatus.AVAILABLE, RoomCategory.LUX, RoomType.DOUBLE),
                    new Room(2, null, RoomStatus.AVAILABLE, RoomCategory.COMFORT, RoomType.DOUBLE),
                    new Room(3, null, RoomStatus.AVAILABLE, RoomCategory.NORMAL, RoomType.DOUBLE),
                    new Room(4, null, RoomStatus.AVAILABLE, RoomCategory.LUX, RoomType.DOUBLE),
                    new Room(5, null, RoomStatus.AVAILABLE, RoomCategory.LUX, RoomType.SINGLE)
            );

            Hotel hotel = new Hotel();
            hotel.setName("IlmNajot");
            hotel.setDescription("this hotel is really cool");
            hotel.setAddress("Tashkent Yunusobot 5-mavze, 13/5");
            hotel.setCity("Tashkent");
            hotel.setCity("Uzbekistan");
            hotel.setPhone("+998991004020");
            hotel.setRooms(roomList);
            hotelRepository.save(hotel);
        }
    }
}
