package uz.ilmnajot.revolution_task.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.revolution_task.model.request.ReservationRequest;
import uz.ilmnajot.revolution_task.service.interfaces.ReservationService;
import uz.ilmnajot.revolution_task.model.common.ApiResponse;

@RestController
@RequestMapping("/api/reserve")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

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
