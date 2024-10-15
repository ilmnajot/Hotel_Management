package uz.ilmnajot.revolution_task.service.interfaces;

import uz.ilmnajot.revolution_task.model.request.SignUpRequest;
import uz.ilmnajot.revolution_task.model.request.UserSignInRequest;
import uz.ilmnajot.revolution_task.template.common.ApiResponse;

public interface AuthService {

    ApiResponse signUp(SignUpRequest request);

    ApiResponse signIn(UserSignInRequest request);

    ApiResponse verify(String username, String verificationCode);
}
