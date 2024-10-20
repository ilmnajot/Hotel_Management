package uz.ilmnajot.revolution_task.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.revolution_task.payload.request.RoomRequest;
import uz.ilmnajot.revolution_task.service.interfaces.RoomService;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;

@SecurityRequirement(name="Bearer")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @PreAuthorize("hasAnyAuthority('ADD_ROOM')")
    @PostMapping("/addRoom")
    public HttpEntity<?> addRoom(@RequestBody RoomRequest request) {
        ApiResponse apiResponse = roomService.addRoom(request);
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('EDIT_ROOM')")
    @PutMapping("/update/{roomId}")
    public HttpEntity<?> updateRoom(@PathVariable("roomId") Long roomId,
                                    @RequestBody RoomRequest request) {
        ApiResponse apiResponse = roomService.updateRoom(roomId, request);
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAnyAuthority('GET_ROOM')")
    @GetMapping("/getRoom/{roomId}")
    public HttpEntity<?> getRoom(@PathVariable("roomId") Long roomId) {
        ApiResponse room = roomService.getRoom(roomId);
        return ResponseEntity.ok(room);
    }

    @PreAuthorize("hasAnyAuthority('GET_ROOMS')")
    @GetMapping("/getRooms")
    public HttpEntity<ApiResponse> getRooms(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size) {
        ApiResponse rooms = roomService.getRooms(page, size);
        return ResponseEntity.ok(rooms);
    }

    @PreAuthorize("hasAnyAuthority('GET_AVAILABLE_ROOMS')")
    @GetMapping("/getAvailableRooms")
    public HttpEntity<ApiResponse> getAvailableRooms(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        ApiResponse rooms = roomService.getAvailableRooms(page, size);
        return ResponseEntity.ok(rooms);
    }

    @PreAuthorize("hasAnyAuthority('GET_REMOVED_ROOMS')")
    @GetMapping("/getRemovedRooms")
    public HttpEntity<ApiResponse> getRemovedRooms(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size) {
        ApiResponse rooms = roomService.getRemovedRooms(page, size);
        return ResponseEntity.ok(rooms);
    }


    @PreAuthorize("hasAnyAuthority('DELETE_ROOM')")
    @DeleteMapping("/removeRoom/{roomId}")
    public HttpEntity<?> removeRoom(@PathVariable("roomId") Long roomId) {
        ApiResponse apiResponse = roomService.removeRoom(roomId);
        return ResponseEntity.ok(apiResponse);
    }

}
