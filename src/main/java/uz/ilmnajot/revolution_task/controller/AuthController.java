package uz.ilmnajot.revolution_task.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.revolution_task.payload.request.SignUpRequest;
import uz.ilmnajot.revolution_task.payload.request.UserSignInRequest;
import uz.ilmnajot.revolution_task.service.interfaces.AuthService;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;

@RequiredArgsConstructor
//@SecurityRequirement(name = "Bearer")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public HttpEntity<ApiResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        ApiResponse apiResponse = authService.signUp(request);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/verify")
    public HttpEntity<ApiResponse> verify(@RequestParam("username") String username,
                                          @RequestParam("verificationCode") String verificationCode) {
        ApiResponse apiResponse = authService.verify(username, verificationCode);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/signIn")
    public HttpEntity<ApiResponse> signIn(@RequestBody UserSignInRequest request) {
        ApiResponse apiResponse = authService.signIn(request);
        return ResponseEntity.ok(apiResponse);
    }
}
