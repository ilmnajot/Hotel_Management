package uz.ilmnajot.revolution_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.entity.auth.Role;
import uz.ilmnajot.revolution_task.entity.auth.User;
import uz.ilmnajot.revolution_task.exception.AlreadyExistsException;
import uz.ilmnajot.revolution_task.exception.ForbiddenException;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.mapper.UserMapper;
import uz.ilmnajot.revolution_task.model.request.SignUpRequest;
import uz.ilmnajot.revolution_task.model.request.UserRequest;
import uz.ilmnajot.revolution_task.model.request.UserSignInRequest;
import uz.ilmnajot.revolution_task.model.response.AuthResponse;
import uz.ilmnajot.revolution_task.repository.RoleRepository;
import uz.ilmnajot.revolution_task.repository.UserRepository;
import uz.ilmnajot.revolution_task.security.JwtProvider;
import uz.ilmnajot.revolution_task.service.interfaces.AuthService;
import uz.ilmnajot.revolution_task.model.common.ApiResponse;
import uz.ilmnajot.revolution_task.utils.RestConstant;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;



    @Value("${spring.mail.username}")
    private String mail;

    @Override
    public ApiResponse signUp(SignUpRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isPresent()) {
            throw new AlreadyExistsException("user with username " + request.getUsername() + " already exists");
        }
        Role role = roleRepository.findByName(RestConstant.USER).orElseThrow(()
                -> new NotFoundException("role not found"));

        User user = new User();
        user.setFName(request.getFName());
        user.setLName(request.getLName());
        user.setUsername(request.getUsername());
//        user.setVerificationCode(request.getVerificationCode());
        if (!request.getPassword().equals(request.getRepeatPassword())) {

            throw new ForbiddenException("not matching passwords", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        user.setEnabled(false);
        String verificationCode = generateVerificationCode();
        user.setVerificationCode(verificationCode);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getUsername());
        message.setFrom("ilmnajot2021@gmail.com");
        message.setSubject("Test Email");
        message.setText("Verification Code: " + verificationCode);
        try {

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
        User addedUser = userRepository.save(user);
        UserRequest mapperRequest = userMapper.toRequest(addedUser);
        return new ApiResponse(true, "the code is sent to your email to verify in 15 minutes", mapperRequest);
    }

    @Override
    public ApiResponse signIn(UserSignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new NotFoundException("username not found"));

        if (!user.isEnabled()) {
            throw new ForbiddenException("you are not verified yet", HttpStatus.BAD_REQUEST);
        }
        String token = jwtProvider.generateAccessToken(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        return new ApiResponse(true, "token: ", authResponse);
    }

    @Override
    public ApiResponse verify(String username, String verificationCode) {
        User user = userRepository.findByUsername(username).orElseThrow(()
                -> new NotFoundException("user not found"));
        if (user.getVerificationCode() != null && user.getVerificationCode().equals(verificationCode)) {
            user.setEnabled(true);
            userRepository.save(user);
            return new ApiResponse(true, "verified",HttpStatus.OK);
        }
        throw new ForbiddenException("failed", HttpStatus.BAD_REQUEST);
    }


    public String generateVerificationCode() {
        Random random = new Random();
        int randomCode = 100000 + random.nextInt(900000);
        return String.valueOf(randomCode);
    }





}
