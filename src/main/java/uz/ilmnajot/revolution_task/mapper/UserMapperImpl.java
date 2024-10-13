package uz.ilmnajot.revolution_task.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.ilmnajot.revolution_task.entity.auth.Role;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.model.request.UserRequest;
import uz.ilmnajot.revolution_task.repository.RoleRepository;
import uz.ilmnajot.revolution_task.utils.RestConstant;

@Component
public class UserMapperImpl implements UserMapper {

    private final RoleRepository roleRepository;

    public UserMapperImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public UserRequest toRequest(User user){
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName(user.getFirstName());
        userRequest.setLastName(user.getLastName());
        userRequest.setUsername(user.getUsername());
        Role role = roleRepository.findByName(RestConstant.USER).orElseThrow(()
                -> new NotFoundException("role not found"));
        userRequest.setRoleId(role.getId());
        return userRequest;
    }

    public User toUser(UserRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        Role role = roleRepository.findByName(RestConstant.USER).orElseThrow(()
                -> new NotFoundException("role not found"));
        user.setRole(role);
        return user;
    }

}
