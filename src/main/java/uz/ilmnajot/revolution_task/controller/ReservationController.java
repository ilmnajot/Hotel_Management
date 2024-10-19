package uz.ilmnajot.revolution_task.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.revolution_task.payload.request.ReservationRequest;
import uz.ilmnajot.revolution_task.service.interfaces.ReservationService;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;
import uz.ilmnajot.revolution_task.validation.CheckAuthority;

@RestController
@RequestMapping("/api/reserve")
@SecurityRequirement(name="BearerAuth")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @CheckAuthority("BOOK_ROOM")
    @PostMapping("/bookRoom")
    public HttpEntity<ApiResponse> bookRoom(@RequestBody ReservationRequest request) {
        ApiResponse apiResponse = reservationService.bookRoom(
                request.getUserId(),
                request.getRoomId(),
                request.getCheckInDate(),
                request.getCheckOutDate());
        return ResponseEntity.ok(apiResponse);
    }

}
