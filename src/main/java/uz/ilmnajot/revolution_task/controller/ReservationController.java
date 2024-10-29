package uz.ilmnajot.revolution_task.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.PreDestroy;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.revolution_task.payload.request.ReservationRequest;
import uz.ilmnajot.revolution_task.service.interfaces.ReservationService;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;

@RestController
@RequestMapping("/api/reserve")
@SecurityRequirement(name="Bearer")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PreAuthorize("hasAnyAuthority('BOOK_ROOM')")
    @PostMapping("/bookRoom")
    public HttpEntity<ApiResponse> bookRoom(@RequestBody ReservationRequest request) {
        ApiResponse apiResponse = reservationService.bookRoom(
                request.getUserId(),
                request.getRoomId(),
                request.getCheckInDate(),
                request.getCheckOutDate());
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('BOOK_ROOM')")
    @GetMapping("/getInfo/{userId}")
    public HttpEntity<ApiResponse> getInfo(@PathVariable("userId") Long userId) {
        ApiResponse apiResponse = reservationService.getInfo(userId);
        return ResponseEntity.ok(apiResponse);
    }

}
