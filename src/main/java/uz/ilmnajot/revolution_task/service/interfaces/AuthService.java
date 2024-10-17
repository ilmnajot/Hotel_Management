package uz.ilmnajot.revolution_task.service.interfaces;

import uz.ilmnajot.revolution_task.payload.request.SignUpRequest;
import uz.ilmnajot.revolution_task.payload.request.UserSignInRequest;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;

public interface AuthService {

    ApiResponse signUp(SignUpRequest request);

    ApiResponse signIn(UserSignInRequest request);

    ApiResponse verify(String username, String verificationCode);
}
