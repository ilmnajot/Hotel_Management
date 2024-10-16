package uz.ilmnajot.revolution_task.mapper;

import org.springframework.stereotype.Component;
import uz.ilmnajot.revolution_task.entity.auth.Role;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.model.request.UserRequest;
import uz.ilmnajot.revolution_task.model.response.UserResponse;
import uz.ilmnajot.revolution_task.repository.RoleRepository;
import uz.ilmnajot.revolution_task.template.UserSession;
import uz.ilmnajot.revolution_task.utils.RestConstant;

@Component
public class UserMapperImpl implements UserMapper {

    private final RoleRepository roleRepository;

    public UserMapperImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public UserRequest toRequest(User user){

        UserRequest userRequest = new UserRequest();
        userRequest.setFName(user.getFName());
        userRequest.setLName(user.getLName());
        userRequest.setUsername(user.getUsername());
        Role role = roleRepository.findByName(RestConstant.USER).orElseThrow(()
                -> new NotFoundException("role not found"));
        userRequest.setRoleId(role.getId());
        userRequest.setAddress(user.getAddress());
        userRequest.setPNumber(user.getPNumber());
        return userRequest;
    }

    public User toUser(UserRequest request){
        User user = new User();
        user.setFName(request.getFName());
        user.setLName(request.getLName());
        user.setUsername(request.getUsername());
        Role role = roleRepository.findByName(RestConstant.USER).orElseThrow(()
                -> new NotFoundException("role not found"));
        user.setRole(role);
        return user;
    }

    @Override
    public UserResponse toResponse(User user) {

        UserResponse response = new UserResponse();
        response.setFName(user.getFName());
        response.setLName(user.getLName());
        response.setUsername(user.getUsername());
        Role role = roleRepository.findByName(RestConstant.USER).orElseThrow(()
                -> new NotFoundException("role not found"));
        response.setRoleId(role.getId());
        response.setAddress(user.getAddress());
        response.setPNumber(user.getPNumber());
        return response;
    }
}
